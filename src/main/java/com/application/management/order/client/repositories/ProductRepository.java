package com.application.management.order.client.repositories;

import com.application.management.order.client.model.Product;

import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, String> {

}
