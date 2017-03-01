package com.application.management.order.client.repositories;


import com.application.management.order.client.model.Client;

import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

// Spring Data JPA CRUD Repository
// Spring Data JPA uses generics and reflection
// to generate the concrete implementation of the interface we define
public interface ClientRepository extends CrudRepository<Client, String> {

}
