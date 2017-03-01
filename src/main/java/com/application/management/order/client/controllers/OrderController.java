package com.application.management.order.client.controllers;


import com.application.management.order.client.exception.CustomException;
import com.application.management.order.client.model.Order;
import com.application.management.order.client.services.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private OrderService orderService;

    @Autowired
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    @RequestMapping(value = "/show", method = RequestMethod.GET, produces = {
            MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public Iterable<Order> getAllClients(@RequestParam(value = "sortTerm",
            defaultValue = "default_orders_sort") String sortTerm) {
        return orderService.sortOrdersBySortTerm(sortTerm);
    }

    @RequestMapping(value = "/order/add", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void addNewOrder(String productBarcode,
                            String clientSecurityId,
                            Integer productQuantity,
                            Date orderTransactionDate) throws Exception {

        try {
            orderService.addNewOderFromForm(productBarcode, clientSecurityId,
                    productQuantity, orderTransactionDate);
        } catch (CustomException e) {
            throw e;
        }
    }

}
