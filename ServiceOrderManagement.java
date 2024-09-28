package com.carsolutions;
/*
 * Name: <Buğra TUTUMLU>
 * Number: <8230800>
 * Class: <LEI_PP>
 */
/**
 * Manages the service orders.
 */
public class ServiceOrderManagement {
    private ServiceOrder[] serviceOrders;
    private int orderCount;

    /**
     * Constructs a new ServiceOrderManagement instance.
     */
    public ServiceOrderManagement() {
        this.serviceOrders = new ServiceOrder[100]; // Fixed size array for simplicity
        this.orderCount = 0;
    }

    /**
     * Creates a new service order.
     *
     * @param order the service order to create
     */
    public void createServiceOrder(ServiceOrder order) {
        if (orderCount < serviceOrders.length) {
            serviceOrders[orderCount++] = order;
        } else {
            System.out.println("Service order list is full");
        }
    }

    /**
     * Updates an existing service order.
     *
     * @param id the unique identifier of the service order to update
     * @param updatedOrder the updated service order
     */
    public void updateServiceOrder(String id, ServiceOrder updatedOrder) {
        for (int i = 0; i < orderCount; i++) {
            if (serviceOrders[i].getId().equals(id)) {
                serviceOrders[i] = updatedOrder;
                break;
            }
        }
    }

    /**
     * Deletes a service order by ID.
     *
     * @param id the unique identifier of the service order to delete
     */
    public void deleteServiceOrder(String id) {
        for (int i = 0; i < orderCount; i++) {
            if (serviceOrders[i].getId().equals(id)) {
                serviceOrders[i] = serviceOrders[--orderCount];
                serviceOrders[orderCount] = null;
                break;
            }
        }
    }

    /**
     * Gets a service order by ID.
     *
     * @param id the unique identifier of the service order to get
     * @return the service order with the specified ID, or null if not found
     */
    public ServiceOrder getServiceOrder(String id) {
        for (int i = 0; i < orderCount; i++) {
            if (serviceOrders[i].getId().equals(id)) {
                return serviceOrders[i];
            }
        }
        return null;
    }

    /**
     * Gets all service orders.
     *
     * @return an array of all service orders
     */
    public ServiceOrder[] getAllServiceOrders() {
        ServiceOrder[] activeOrders = new ServiceOrder[orderCount];
        System.arraycopy(serviceOrders, 0, activeOrders, 0, orderCount);
        return activeOrders;
    }
}
