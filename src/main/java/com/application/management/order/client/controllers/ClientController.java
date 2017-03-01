package com.application.management.order.client.controllers;

import com.application.management.order.client.exception.CustomException;
import com.application.management.order.client.model.Client;
import com.application.management.order.client.services.ClientService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clients")
public class ClientController {

    private ClientService clientService;

    @Autowired
    public void setClientService(ClientService clientService) {
        this.clientService = clientService;
    }

    @RequestMapping(value = "/show", method = RequestMethod.
            GET, produces = {
            MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public Iterable<Client> getAllClients() {
        return clientService.getAllClients();
    }

    /**
     * Retrieves client information from a database by using the client id
     */
    @RequestMapping(value = "/client/{clientSecurityId}", method = RequestMethod.GET, produces = {
            MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Client> getClientById(@PathVariable(value = "clientSecurityId")
                                                        String clientSecurityId) throws CustomException {
        Client client;
        try {
            client = clientService.getClientById(clientSecurityId);
        } catch (CustomException e) {
            throw e;
        }
        return ResponseEntity.ok(client);

    }


    @RequestMapping(value = "/client/edit", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void editClient(Client client) throws CustomException {
        try {
            clientService.updateClient(client);
        } catch (CustomException e) {
            throw e;
        }
    }


    @RequestMapping(value = "/client/add", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void addClient(String clientFirstName,
                          String clientLastName,
                          String clientPhoneNumber,
                          String clientCountry,
                          String clientStreetAddress
    ) throws CustomException {
        try {
            clientService.addNewClient(new Client(clientFirstName, clientLastName, clientPhoneNumber,
                    clientCountry, clientStreetAddress));
        } catch (CustomException e) {
            throw e;
        }

    }

    // For future reference
    /*
    * The client parameter does not receive its id due to the fact that in the client class
    * the set method for id is private. In order to leave the method unexposed
    * the id will be passed as a parameter *//*
    @RequestMapping(value = "/client/edit", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void editClient(String clientSecurityId, Client client) throws Exception{
        System.out.println(clientSecurityId);
        System.out.println(client);
        if(clientService.updateClient(new Client())){
            throw new CustomException("Client was not be updated for some reason");
        }

    }*/

    // not required for the task
    //@RequestMapping(value = "/client/delete", method = RequestMethod.POST)
    //@ResponseStatus(HttpStatus.OK)
    //public void deleteClient(String clientSecurityId) throws Exception {
    //    System.out.println(clientSecurityId);
    //    if (!clientService.deleteClientBySecurityId(clientSecurityId)) {
    //        throw new CustomException("Client was not be deleted for some reason");
    //    }
    //
    //}

}
