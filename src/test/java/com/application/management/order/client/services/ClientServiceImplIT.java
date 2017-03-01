package com.application.management.order.client.services;

import com.application.management.order.client.exception.CustomException;
import com.application.management.order.client.model.Client;
import com.application.management.order.client.repositories.ClientRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Integration tests for ClientServiceImpl
 */
@RunWith(SpringRunner.class)
@DataJpaTest(includeFilters = @ComponentScan.Filter(
        type = FilterType.ASSIGNABLE_TYPE, classes = ClientServiceImpl.class))
public class ClientServiceImplIT {

    private ClientServiceImpl clientServiceImpl;
    private ClientRepository clientRepository;

    @Autowired
    public void setClientServiceImpl(ClientServiceImpl clientServiceImpl) {
        this.clientServiceImpl = clientServiceImpl;
    }

    @Autowired
    public void setClientRepository(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    // Sets up data before each test
    @Before
    public void setUp() {

        String[] uuids = new String[]{"067e6162-3b6f-4ae2-a171-2470b63dff00",
                "067e6162-3b6f-4ae2-a171-2470b63dff01",
                "e045dae7-c1a3-4907-b629-0c3024ff8bfc",
                "2e154499-415a-431f-bdbc-10c9c39d803e",
                "90d1ed7b-014b-44c9-8384-27eb0103aca1"};
        Client client1 = new Client("John", "Smith", "10124234", "CH", "Some Address");
        Client client2 = new Client("Name 1", "Surname 1", "101242345", "Belgium", "Some Address");
        Client client3 = new Client("Name 2", "Surname 2", "101242346", "Belgium", "Some Address");
        client1.setClientSecurityId(uuids[0]);
        client2.setClientSecurityId(uuids[1]);
        client3.setClientSecurityId(uuids[2]);
        clientRepository.save(client1);
        clientRepository.save(client2);
        clientRepository.save(client3);
    }

    //-------------------------------------------------------------------------

    /*
     * getClientById(...)
     */
    @Test(expected = CustomException.class)
    public void getClientWithInvalidId1() throws Exception {
        // not in a database
        String notInDatabase = "e045dae7-c1a3-4907-b629-0c3024ff8bf1";
        clientServiceImpl.getClientById(notInDatabase);
    }

    @Test(expected = CustomException.class)
    public void getClientWithInvalidId2() throws Exception {
        // more characters
        String invalidUuid = "e045dae7-c1a3-4907-b629-0c3024ff8bfq685r";
        Client client = clientServiceImpl.getClientById(invalidUuid);
    }

    @Test
    public void getClientWithValidId() throws Exception {
        // valid
        String uuidInDatabase = "067e6162-3b6f-4ae2-a171-2470b63dff01";
        Client client = clientServiceImpl.getClientById(uuidInDatabase);
    }

    //-------------------------------------------------------------------------
    @Test(expected = CustomException.class)
    public void addClientWithInvalidCountry() throws Exception {
        // invalid country
        Client client = new Client("Name", "Surname", "10124234", "QQ", "Some Address");
        clientServiceImpl.addNewClient(client);
    }

    @Test(expected = CustomException.class)
    public void addClientWithInvalidNumber() throws Exception {
        // invalid phone
        Client client = new Client("Name", "Surname", "j1449342", "CH", "Some Address");
        clientServiceImpl.addNewClient(client);
    }

    @Test(expected = CustomException.class)
    public void addClientWithInvalidFirstName() throws Exception {
        Client client = new Client("", "Surname", "10124234", "CH", "Some Address");
        clientServiceImpl.addNewClient(client);
    }

    @Test(expected = CustomException.class)
    public void addClientWithInvalidLastName() throws Exception {
        Client client = new Client("Name", "", "10124234", "CH", "Some Address");
        clientServiceImpl.addNewClient(client);
    }

    @Test(expected = CustomException.class)
    public void addClientWithInvalidAddress() throws Exception {
        Client client = new Client("Name", "Surname", "10124234", "CH", null);
        clientServiceImpl.addNewClient(client);
    }

    @Test
    public void addClientWithValidInfo() throws Exception {
        Client client = new Client("Name", "Surname", "10124234", "CH", "Address");
        clientServiceImpl.addNewClient(client);
    }

    //-------------------------------------------------------------------------

    /*
     * updateClient(...)
     */

    @Test
    public void updateClientWithValidInfo() throws Exception {
        Client client = new Client("Name", "Changed Surname", "10124234", "CH", "Address");
        client.setClientSecurityId("067e6162-3b6f-4ae2-a171-2470b63dff00");
        clientServiceImpl.updateClient(client);

    }


    @Test(expected = CustomException.class)
    public void updateClientWithInvalidCountry() throws Exception {
        Client client = new Client("Name", "Changed Surname", "10124234", "PQ", "Address");
        client.setClientSecurityId("067e6162-3b6f-4ae2-a171-2470b63dff00");
        clientServiceImpl.updateClient(client);
    }

    @Test(expected = CustomException.class)
    public void updateClientWithInvalidId() throws Exception {
        Client client = new Client("Name", "Changed Surname", "10124234", "CH", "Address");
        client.setClientSecurityId("067e6162-3b6f-4ae2-a171-2470b63dff11");
        clientServiceImpl.updateClient(client);
    }


}