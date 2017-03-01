package com.application.management.order.client.model;


import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "clients")
public class Client {

    /*
     * @NotNull annotations used more like hints
     * if field is null, entry will not be inserted into a database
     * */

    @NotNull
    @Id
    private String clientSecurityId;
    @NotNull
    private String clientFirstName;
    @NotNull
    private String clientLastName;
    @NotNull
    private String clientPhoneNumber;
    @NotNull
    private String clientCountry;
    @NotNull
    private String clientStreetAddress;

    Client() {
    }

    public Client(String clientFirstName, String clientLastName, String clientPhoneNumber,
                  String clientCountry, String clientStreetAddress) {
        this.clientSecurityId = UUID.randomUUID().toString();
        this.clientFirstName = clientFirstName;
        this.clientLastName = clientLastName;
        this.clientPhoneNumber = clientPhoneNumber;
        this.clientCountry = clientCountry;
        this.clientStreetAddress = clientStreetAddress;
    }

    public String getClientSecurityId() {
        return clientSecurityId;
    }

    // the method needs to be public for a form to be submitted as a whole
    public void setClientSecurityId(String clientSecurityId) {
        this.clientSecurityId = clientSecurityId;
    }

    public String getClientFirstName() {
        return clientFirstName;
    }

    public void setClientFirstName(String clientFirstName) {
        this.clientFirstName = clientFirstName;
    }

    public String getClientLastName() {
        return clientLastName;
    }

    public void setClientLastName(String clientLastName) {
        this.clientLastName = clientLastName;
    }

    public String getClientPhoneNumber() {
        return clientPhoneNumber;
    }

    public void setClientPhoneNumber(String clientPhoneNumber) {
        this.clientPhoneNumber = clientPhoneNumber;
    }

    public String getClientCountry() {
        return clientCountry;
    }

    public void setClientCountry(String clientCountry) {
        this.clientCountry = clientCountry;
    }

    public String getClientStreetAddress() {
        return clientStreetAddress;
    }

    public void setClientStreetAddress(String clientStreetAddress) {
        this.clientStreetAddress = clientStreetAddress;
    }


    @Override
    public String toString() {
        return "Client{" +
                "clientSecurityId='" + clientSecurityId + '\'' +
                ", clientFirstName='" + clientFirstName + '\'' +
                ", clientLastName='" + clientLastName + '\'' +
                ", clientPhoneNumber='" + clientPhoneNumber + '\'' +
                ", clientCountry='" + clientCountry + '\'' +
                ", clientStreetAddress='" + clientStreetAddress + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Client client = (Client) o;

        if (clientSecurityId != null ? !clientSecurityId.equals(client.clientSecurityId) : client.clientSecurityId != null)
            return false;
        if (clientFirstName != null ? !clientFirstName.equals(client.clientFirstName) : client.clientFirstName != null)
            return false;
        if (clientLastName != null ? !clientLastName.equals(client.clientLastName) : client.clientLastName != null)
            return false;
        if (clientPhoneNumber != null ? !clientPhoneNumber.equals(client.clientPhoneNumber) : client.clientPhoneNumber != null)
            return false;
        if (clientCountry != null ? !clientCountry.equals(client.clientCountry) : client.clientCountry != null)
            return false;
        return clientStreetAddress != null ? clientStreetAddress.equals(client.clientStreetAddress) : client.clientStreetAddress == null;

    }

    @Override
    public int hashCode() {
        int result = clientSecurityId != null ? clientSecurityId.hashCode() : 0;
        result = 31 * result + (clientFirstName != null ? clientFirstName.hashCode() : 0);
        result = 31 * result + (clientLastName != null ? clientLastName.hashCode() : 0);
        result = 31 * result + (clientPhoneNumber != null ? clientPhoneNumber.hashCode() : 0);
        result = 31 * result + (clientCountry != null ? clientCountry.hashCode() : 0);
        result = 31 * result + (clientStreetAddress != null ? clientStreetAddress.hashCode() : 0);
        return result;
    }
}
