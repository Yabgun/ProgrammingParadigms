package com.carsolutions;
/*
 * Name: <Buğra TUTUMLU>
 * Number: <8230800>
 * Class: <LEI_PP>
 */
/**
 * Represents a client associated with a service order.
 */
public abstract class Client {
    private String clientId;
    private String name;

    /**
     * Constructs a new Client with the specified client ID and name.
     *
     * @param clientId the unique identifier of the client
     * @param name the name of the client
     */
    public Client(String clientId, String name) {
        this.clientId = clientId;
        this.name = name;
    }

    public String getClientId() {
        return clientId;
    }

    public String getName() {
        return name;
    }
}
