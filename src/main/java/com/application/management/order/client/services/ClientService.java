package com.application.management.order.client.services;


import com.application.management.order.client.exception.CustomException;
import com.application.management.order.client.model.Client;

import java.util.UUID;

public interface ClientService {

    /**
     * @return data type that implements the Iterable interface with all clients in it
     */
    Iterable<Client> getAllClients();

    /**
     * @throws com.application.management.order.client.exception.CustomException if client information
     *                                                                           is not valid
     */
    void addNewClient(Client client) throws CustomException;

    /**
     * @return Client object if client was found by the security id.
     * @throws CustomException If client is not found, then CustomException is thrown
     */
    Client getClientById(String clientSecurityId) throws CustomException;

    /**
     * @throws com.application.management.order.client.exception.CustomException if client information
     *                                                                           is not valid
     */
    void updateClient(Client client) throws CustomException;


}
