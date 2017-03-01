package com.application.management.order.client.services;


import com.application.management.order.client.exception.CustomException;
import com.application.management.order.client.model.Client;
import com.application.management.order.client.model.Order;
import com.application.management.order.client.model.Product;
import com.application.management.order.client.repositories.OrderRepository;
import com.application.management.order.client.util.ProductAndOrderValidation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Date;

/**
 * Business logic for orders
 */
@Service
public class OrderServiceImpl implements OrderService {

    private static final String DEFAULT_SORT = "default_orders_sort";
    private static final String CLIENT_FIRST_NAME_ASC = "client_first_name_asc";
    private static final String CLIENT_LAST_NAME_ASC = "client_last_name_asc";
    private static final String PRODUCT_BARCODE_ASC = "product_barcode_asc";
    private static final String ORDER_DATE_DESC = "order_date_desc";
    private static final String PRODUCT_NAME_ASC = "product_name_asc";
    private static final String ORDER_NUMBER_ASC = "order_number_asc";

    private OrderRepository orderRepository;
    private ClientService clientService;
    private ProductService productService;

    @Autowired
    public void setOrderRepository(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Autowired
    public void setClientService(ClientService clientService) {
        this.clientService = clientService;
    }

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }


    @Override
    public Iterable<Order> sortOrdersBySortTerm(String sortTerm) {
        Iterable<Order> orders;
        switch (sortTerm) {
            case DEFAULT_SORT:
                orders = sortOrdersByTransactionDateAndProductName();
                break;
            case CLIENT_FIRST_NAME_ASC:
                orders = sortOrdersByClientFirstName();
                break;
            case CLIENT_LAST_NAME_ASC:
                orders = sortOrdersByClientLastName();
                break;
            case PRODUCT_BARCODE_ASC:
                orders = sortOrdersByProductBarcode();
                break;
            case PRODUCT_NAME_ASC:
                orders = sortOrdersByProductName();
                break;
            case ORDER_DATE_DESC:
                orders = sortOrdersByTransactionDate();
                break;
            case ORDER_NUMBER_ASC:
                orders = sortOrdersByOrderNumber();
                break;
            default:
                orders = sortOrdersByTransactionDateAndProductName();
                break;
        }
        return orders;
    }

    @Override
    public void addNewOderFromForm(String productBarcode, String clientSecurityId,
                                   Integer productQuantity, Date orderTransactionDate) throws CustomException {
        try {
            if (productQuantity < 1) {
                throw new CustomException("Order quantity is invalid " + productQuantity);
            }
            Product product = productService.getProductByBarcode(productBarcode);
            Client client = clientService.getClientById(clientSecurityId);
            BigDecimal orderPrice = calculateOrderPrice(productQuantity, product.getProductBasePrice());
            Order order = new Order(orderPrice, orderTransactionDate, product, client);
            validateOrder(order);
            orderRepository.save(order);

        } catch (CustomException e) {
            throw e;
        }
    }

    /**
     * Gets orders from a database so that they are sorted by order transaction date
     * and product name in ascending order
     */
    private Iterable<Order> sortOrdersByTransactionDateAndProductName() {
        PageRequest request = new PageRequest(
                0,
                Integer.MAX_VALUE,
                new Sort(Sort.Direction.DESC, "orderTransactionDate", "product.productName")
        );
        return orderRepository.findAll(request).getContent();
    }

    /*
    * Description is basically the same for other methods
    * What they do can be understood from their names
    * */

    private Iterable<Order> sortOrdersByClientFirstName() {
        PageRequest request = new PageRequest(
                0,
                Integer.MAX_VALUE,
                new Sort(Sort.Direction.ASC, "client.clientFirstName")
        );
        return orderRepository.findAll(request).getContent();
    }

    private Iterable<Order> sortOrdersByClientLastName() {
        PageRequest request = new PageRequest(
                0,
                Integer.MAX_VALUE,
                new Sort(Sort.Direction.ASC, "client.clientLastName")
        );
        return orderRepository.findAll(request).getContent();
    }

    private Iterable<Order> sortOrdersByProductBarcode() {
        PageRequest request = new PageRequest(
                0,
                Integer.MAX_VALUE,
                new Sort(Sort.Direction.ASC, "product.productBarcode")
        );
        return orderRepository.findAll(request).getContent();
    }

    private Iterable<Order> sortOrdersByProductName() {
        PageRequest request = new PageRequest(
                0,
                Integer.MAX_VALUE,
                new Sort(Sort.Direction.ASC, "product.productName")
        );
        return orderRepository.findAll(request).getContent();
    }

    private Iterable<Order> sortOrdersByTransactionDate() {
        PageRequest request = new PageRequest(
                0,
                Integer.MAX_VALUE,
                new Sort(Sort.Direction.DESC, "orderTransactionDate")
        );
        return orderRepository.findAll(request).getContent();
    }

    private Iterable<Order> sortOrdersByOrderNumber() {
        PageRequest request = new PageRequest(
                0,
                Integer.MAX_VALUE,
                new Sort(Sort.Direction.ASC, "orderNumber")
        );
        return orderRepository.findAll(request).getContent();
    }

    /**
     * Product and Client, Price should already be validated
     */
    private void validateOrder(Order order) throws CustomException {
        if (order == null) {
            throw new CustomException("Order is null");
        }
        Date sqlDate = order.getOrderTransactionDate();
        if (!checkDate(sqlDate)) {
            throw new CustomException("Order transaction date is invalid " + sqlDate);
        }
    }

    private BigDecimal calculateOrderPrice(int quantity, BigDecimal productPrice) {
        BigDecimal price = new BigDecimal(quantity).multiply(productPrice);
        price = price.setScale(2, BigDecimal.ROUND_HALF_UP);
        return price;
    }

    private boolean checkDate(Date date) throws CustomException {
        boolean result = true;
        if (date == null) {
            result = false;
        } else if (!ProductAndOrderValidation.isDateValid(date.toString())) {
            result = false;
        }
        return result;
    }

}
