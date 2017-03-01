package com.application.management.order.client.services;

import com.application.management.order.client.exception.CustomException;
import com.application.management.order.client.model.Client;
import com.application.management.order.client.model.Product;
import com.application.management.order.client.repositories.ClientRepository;
import com.application.management.order.client.repositories.ProductRepository;
import com.application.management.order.client.util.CommonUtil;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.sql.Date;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest(includeFilters = @ComponentScan.Filter(
        type = FilterType.ASSIGNABLE_TYPE, classes = {OrderServiceImpl.class, ClientServiceImpl.class,
        ProductServiceImpl.class}))
public class OrderServiceImplIT {

    private ProductRepository productRepository;
    private ClientRepository clientRepository;
    private OrderServiceImpl orderServiceImpl;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Autowired
    public void setClientRepository(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Autowired
    public void setOrderServiceImpl(OrderServiceImpl orderServiceImpl) {
        this.orderServiceImpl = orderServiceImpl;
    }

    @Before
    public void setUp() {
        String[] uuids = new String[]{"067e6162-3b6f-4ae2-a171-2470b63dff00"};
        Client client1 = new Client("John", "Smith", "10124234", "CH", "Some Address");
        client1.setClientSecurityId(uuids[0]);
        clientRepository.save(client1);

        String[] barcodes = new String[]{"1234567891"};
        Product product1 = new Product("Product Name", new BigDecimal("12.10"),
                "Product Description", "2010-10-20");
        product1.setProductBarcode(barcodes[0]);
        productRepository.save(product1);
    }

    //-------------------------------------------------------------------------

    /*
     * addNewOrderFromForm(...)
     * */

    @Test(expected = CustomException.class)
    public void addNewOderFromFormWithInvalidBarcode() throws Exception {
        String transactionDateStr = "2010-08-03";
        Date transactionDate = CommonUtil.fromStringToSqlDate(transactionDateStr);
        int quantity = 50;
        String barcode = "1238567891";
        String uuid = "067e6162-3b6f-4ae2-a171-2470b63dff00";
        orderServiceImpl.addNewOderFromForm(barcode, uuid, quantity, transactionDate);

    }

    @Test(expected = CustomException.class)
    public void addNewOderFromFormWithInvalidClientId() throws Exception {
        String transactionDateStr = "2012-08-03";
        Date transactionDate = CommonUtil.fromStringToSqlDate(transactionDateStr);
        int quantity = 60;
        String barcode = "1234567891";
        String uuid = "1238567891";
        orderServiceImpl.addNewOderFromForm(barcode, uuid, quantity, transactionDate);
    }

    @Test(expected = CustomException.class)
    public void addNewOderFromFormWithInvalidQuantity() throws Exception {
        String transactionDateStr = "2012-08-10";
        Date transactionDate = CommonUtil.fromStringToSqlDate(transactionDateStr);
        int quantity = -1;
        String barcode = "1234567891";
        String uuid = "067e6162-3b6f-4ae2-a171-2470b63dff00";
        orderServiceImpl.addNewOderFromForm(barcode, uuid, quantity, transactionDate);
    }

    @Test(expected = CustomException.class)
    public void addNewOderFromFormWithInvalidDateNull() throws Exception {
        Date transactionDate = null;
        int quantity = 60;
        String barcode = "1234567891";
        String uuid = "067e6162-3b6f-4ae2-a171-2470b63dff00";
        orderServiceImpl.addNewOderFromForm(barcode, uuid, quantity, transactionDate);
    }

    @Test
    public void addNewOderFromFormWithValidInfo() throws Exception {
        String transactionDateStr = "2012-08-10";
        Date transactionDate = CommonUtil.fromStringToSqlDate(transactionDateStr);
        int quantity = 60;
        String barcode = "1234567891";
        String uuid = "067e6162-3b6f-4ae2-a171-2470b63dff00";
        orderServiceImpl.addNewOderFromForm(barcode, uuid, quantity, transactionDate);
    }
}