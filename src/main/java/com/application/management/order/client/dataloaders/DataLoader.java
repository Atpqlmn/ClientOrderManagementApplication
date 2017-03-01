package com.application.management.order.client.dataloaders;


import com.application.management.order.client.model.Client;
import com.application.management.order.client.model.Order;
import com.application.management.order.client.model.Product;
import com.application.management.order.client.services.ClientService;
import com.application.management.order.client.services.OrderService;
import com.application.management.order.client.services.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

import au.com.bytecode.opencsv.CSVReader;


@Component
public class DataLoader implements ApplicationListener<ContextRefreshedEvent> {

    private OrderService orderService;
    private ClientService clientService;
    private ProductService productService;

    @Autowired
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
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
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

        String productDataFile = getClass().getResource("/mockdata/products_mock_data.csv").getFile();
        String clientDataFile = getClass().getResource("/mockdata/clients_mock_data.csv").getFile();
        String orderDataFile = getClass().getResource("/mockdata/orders_mock_data.csv").getFile();

        Client[] clientsForOrders = populateClients(clientDataFile, 10);
        Product[] productsForOrders = populateProducts(productDataFile, 10);
        populateOrders(orderDataFile, productsForOrders, clientsForOrders);
    }

    /**
     * Populates the clients table in the database
     *
     * @param fileName the file name of clients mock data
     * @param n        the number of clients to return (might be used to populate the orders table)
     * @return an array of clients
     */
    private Client[] populateClients(String fileName, int n) {
        Client[] clients = new Client[n];
        try (FileReader fileReader = new FileReader(fileName);
             CSVReader reader = new CSVReader(fileReader)) {
            List<String[]> data = reader.readAll();
            // the first row contains names of columns
            data.remove(0);
            for (int i = 0; i < data.size(); i++) {
                String[] row = data.get(i);
                Client client = new Client(row[0], row[1], row[2], row[3], row[4]);
                if (clients.length > i) {
                    clients[i] = client;
                }
                clientService.addNewClient(client);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return clients;
    }

    /**
     * Populates the clients table in the database
     *
     * @param fileName the file name of products mock data
     * @param n        the number of products to return (might be used to populate the orders table)
     * @return an array of products
     */
    private Product[] populateProducts(String fileName, int n) {
        Product[] products = new Product[n];
        try (FileReader fileReader = new FileReader(fileName);
             CSVReader reader = new CSVReader(fileReader)) {

            List<String[]> data = reader.readAll();
            // the first row contains names of columns
            data.remove(0);
            for (int i = 0; i < data.size(); i++) {
                String[] row = data.get(i);
                Product product = new Product(row[0], new BigDecimal(row[1]), row[2], row[3]);
                if (products.length > i) {
                    products[i] = product;
                }
                productService.addNewProduct(product, true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return products;
    }

    /**
     * Populates orders into the order table. products.length should be equal to clients.length
     *
     * @param fileName orders mock data
     * @param products an array of products
     * @param clients  an array clients
     */
    private void populateOrders(String fileName, Product[] products, Client[] clients) {
        try (FileReader fileReader = new FileReader(fileName);
             CSVReader reader = new CSVReader(fileReader)) {
            List<String[]> data = reader.readAll();
            // the first row contains names of columns
            data.remove(0);
            for (int i = 0; i < data.size(); i++) {
                String[] row = data.get(i);
                Client client = clients[i];
                Product product = products[i];
                Date dateSql = Date.valueOf(row[0]);
                orderService.addNewOderFromForm(product.getProductBarcode(),
                        client.getClientSecurityId(), 1, dateSql);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
