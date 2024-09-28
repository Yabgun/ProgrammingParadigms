package com.carsolutions;
/*
 * Name: <Buğra TUTUMLU>
 * Number: <8230800>
 * Class: <LEI_PP>
 */
/**
 * Provides statistical information about service orders.
 */
public class Statistics implements IStatistics {
    private ServiceOrderManagement serviceOrderManagement;

    /**
     * Constructs a new Statistics instance with the specified service order management.
     *
     * @param serviceOrderManagement the service order management to use
     */
    public Statistics(ServiceOrderManagement serviceOrderManagement) {
        this.serviceOrderManagement = serviceOrderManagement;
    }

    @Override
    public int getTotalServiceOrders() {
        return serviceOrderManagement.getAllServiceOrders().length;
    }

    @Override
    public int getTotalServices() {
        int totalServices = 0;
        for (ServiceOrder order : serviceOrderManagement.getAllServiceOrders()) {
            for (OrderItem item : order.getItems()) {
                if (item instanceof Service) {
                    totalServices++;
                }
            }
        }
        return totalServices;
    }

    @Override
    public int getTotalParts() {
        int totalParts = 0;
        for (ServiceOrder order : serviceOrderManagement.getAllServiceOrders()) {
            for (OrderItem item : order.getItems()) {
                if (item instanceof Part) {
                    totalParts++;
                }
            }
        }
        return totalParts;
    }
}
