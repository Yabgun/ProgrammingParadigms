package com.carsolutions;
/*
 * Name: <Buğra TUTUMLU>
 * Number: <8230800>
 * Class: <LEI_PP>
 */
/**
 * Represents an individual client.
 */
public class Individual extends Client {
    private String personalId;

    /**
     * Constructs a new Individual client with the specified details.
     *
     * @param clientId the unique identifier of the client
     * @param name the name of the client
     * @param personalId the personal ID of the individual
     */
    public Individual(String clientId, String name, String personalId) {
        super(clientId, name);
        this.personalId = personalId;
    }

    public String getPersonalId() {
        return personalId;
    }
}
