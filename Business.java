package com.carsolutions;
/*
 * Name: <Buğra TUTUMLU>
 * Number: <8230800>
 * Class: <LEI_PP>
 */
/**
 * Represents a business client.
 */
public class Business extends Client {
    private String taxId;

    /**
     * Constructs a new Business client with the specified details.
     *
     * @param clientId the unique identifier of the client
     * @param name the name of the client
     * @param taxId the tax ID of the business
     */
    public Business(String clientId, String name, String taxId) {
        super(clientId, name);
        this.taxId = taxId;
    }

    public String getTaxId() {
        return taxId;
    }
}
