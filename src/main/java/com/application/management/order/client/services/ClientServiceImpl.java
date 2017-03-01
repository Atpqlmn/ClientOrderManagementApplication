package com.application.management.order.client.services;


import com.application.management.order.client.exception.CustomException;
import com.application.management.order.client.model.Client;
import com.application.management.order.client.repositories.ClientRepository;
import com.application.management.order.client.util.ClientValidation;
import com.application.management.order.client.util.CommonUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Business logic for clients.
 * Default constructor is available
 */
@Service
public class ClientServiceImpl implements ClientService {

    private ClientRepository clientRepository;

    @Autowired
    public void setClientRepository(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public Iterable<Client> getAllClients() {
        return clientRepository.findAll();
    }

    @Override
    public void addNewClient(Client client) throws CustomException {
        try {
            validateClientInput(client);
            clientRepository.save(client);
        } catch (CustomException e) {
            throw e;
        }
    }

    @Override
    public Client getClientById(String clientSecurityId) throws CustomException {
        Client client;
        if (ClientValidation.isUUIDValid(clientSecurityId)) {
            client = clientRepository.findOne(clientSecurityId);
            if (client == null) {
                throw new CustomException("Client could not be found");
            }
        } else {
            throw new CustomException("The supplied client id is not valid");
        }
        return client;
    }

    @Override
    public void updateClient(Client client) throws CustomException {
        try {
            validateClientInput(client);
            if (!clientRepository.exists(client.getClientSecurityId())) {
                throw new CustomException("Client could not be found by the supplied id");
            } else {
                clientRepository.save(client);
            }
        } catch (CustomException e) {
            throw e;
        }
    }

    private void validateClientInput(Client client) throws CustomException {
        if (client == null) {
            throw new CustomException("Client is null");
        }

        String securityId = client.getClientSecurityId();
        String country = client.getClientCountry();
        String firstName = client.getClientFirstName();
        String lastName = client.getClientLastName();
        String address = client.getClientStreetAddress();
        String phoneNumber = client.getClientPhoneNumber();

        if (!ClientValidation.isUUIDValid(securityId)) {
            throw new CustomException("Client security ID is not valid " + securityId);
        } else if (!ClientValidation.isCountryCodeValid(country)) {
            throw new CustomException("Client country is not valid");
        } else if (!CommonUtil.validateString(firstName)) {
            throw new CustomException("Client first name is not valid " + firstName);
        } else if (!CommonUtil.validateString(lastName)) {
            throw new CustomException("Client last name is not valid " + lastName);
        } else if (!CommonUtil.validateString(address)) {
            throw new CustomException("Client street address is not valid " + address);
        } else if (!CommonUtil.isNumeric(phoneNumber)) {
            throw new CustomException("Client phone number is not valid " + phoneNumber);
        }
    }
}

