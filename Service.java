package com.carsolutions;
/*
 * Name: <Buğra TUTUMLU>
 * Number: <8230800>
 * Class: <LEI_PP>
 */
/**
 * Represents a service item in a service order.
 */
public class Service extends OrderItem {
    private int executionTime;

    /**
     * Constructs a new Service with the specified description and execution time.
     *
     * @param description the description of the service
     * @param executionTime the execution time in minutes
     */
    public Service(String description, int executionTime) {
        super(description);
        this.executionTime = executionTime;
    }

    public int getExecutionTime() {
        return executionTime;
    }
}
