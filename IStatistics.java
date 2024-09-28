package com.carsolutions;
/*
 * Name: <Buğra TUTUMLU>
 * Number: <8230800>
 * Class: <LEI_PP>
 */
/**
 * Interface for generating statistics about service orders.
 */
public interface IStatistics {
    /**
     * Gets the total number of service orders.
     *
     * @return the total number of service orders
     */
    int getTotalServiceOrders();

    /**
     * Gets the total number of services across all service orders.
     *
     * @return the total number of services
     */
    int getTotalServices();

    /**
     * Gets the total number of parts across all service orders.
     *
     * @return the total number of parts
     */
    int getTotalParts();
}
