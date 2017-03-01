package com.application.management.order.client.services;

import com.application.management.order.client.exception.CustomException;
import com.application.management.order.client.model.Order;

import java.sql.Date;

public interface OrderService {

    /**
     * Sorts orders based on the term that is provided. If the provided term is
     * incorrect meaning not defined in the system, then default sorting is used
     *
     * @return an iterable object that contains orders
     */
    Iterable<Order> sortOrdersBySortTerm(String sortTerm);

    /**
     * Adds an order to the database using information given by the html form
     *
     * @throws  CustomException if order was not added to the database
     */
    void addNewOderFromForm(String productBarcode,
                               String clientSecurityId,
                               Integer productQuantity,
                               Date orderTransactionDate) throws CustomException;

}
