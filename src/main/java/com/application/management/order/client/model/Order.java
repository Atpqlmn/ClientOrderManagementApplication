package com.application.management.order.client.model;


import java.math.BigDecimal;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "orders")
public class Order {

    /*
     * @NotNull annotations used as hints
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderNumber;
    @NotNull
    private BigDecimal orderPrice;

    @NotNull
    private Date orderTransactionDate;

    //unidirectional relationship
    @NotNull
    @ManyToOne
    @JoinColumn(name = "productId", nullable = false)
    private Product product;

    //unidirectional relationship
    @NotNull
    @ManyToOne
    @JoinColumn(name = "clientId", nullable = false)
    private Client client;

    Order() {
    }

    public Order(BigDecimal orderPrice, Date orderTransactionDate, Product product, Client client) {
        this.orderPrice = orderPrice;
        this.orderTransactionDate = orderTransactionDate;
        this.product = product;
        this.client = client;
    }

    public Long getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Long orderNumber) {
        this.orderNumber = orderNumber;
    }

    public BigDecimal getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(BigDecimal orderPrice) {
        this.orderPrice = orderPrice;
    }

    public Date getOrderTransactionDate() {
        return orderTransactionDate;
    }

    public void setOrderTransactionDate(Date orderTransactionDate) {
        this.orderTransactionDate = orderTransactionDate;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (orderNumber != null ? !orderNumber.equals(order.orderNumber) : order.orderNumber != null)
            return false;
        if (orderPrice != null ? !orderPrice.equals(order.orderPrice) : order.orderPrice != null)
            return false;
        if (orderTransactionDate != null ? !orderTransactionDate.equals(order.orderTransactionDate) : order.orderTransactionDate != null)
            return false;
        if (product != null ? !product.equals(order.product) : order.product != null) return false;
        return client != null ? client.equals(order.client) : order.client == null;

    }

    @Override
    public int hashCode() {
        int result = orderNumber != null ? orderNumber.hashCode() : 0;
        result = 31 * result + (orderPrice != null ? orderPrice.hashCode() : 0);
        result = 31 * result + (orderTransactionDate != null ? orderTransactionDate.hashCode() : 0);
        result = 31 * result + (product != null ? product.hashCode() : 0);
        result = 31 * result + (client != null ? client.hashCode() : 0);
        return result;
    }
}
