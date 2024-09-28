package com.carsolutions;
/*
 * Name: <Buğra TUTUMLU>
 * Number: <8230800>
 * Class: <LEI_PP>
 */
import java.util.Date;

/**
 * Represents a service order for a vehicle.
 */
public class ServiceOrder {
    private String id;
    private Date creationDate;
    private Date estimatedCompletionDate;
    private Date actualCompletionDate;
    private String vehicleRegistration;
    private Client client;
    private OrderItem[] items;
    private int itemCount;

    /**
     * Constructs a new ServiceOrder with the specified details.
     *
     * @param id the unique identifier for the service order
     * @param creationDate the date the service order was created
     * @param estimatedCompletionDate the estimated completion date for the service order
     * @param vehicleRegistration the vehicle registration number
     * @param client the client associated with the service order
     */
    public ServiceOrder(String id, Date creationDate, Date estimatedCompletionDate, String vehicleRegistration, Client client) {
        this.id = id;
        this.creationDate = creationDate;
        this.estimatedCompletionDate = estimatedCompletionDate;
        this.vehicleRegistration = vehicleRegistration;
        this.client = client;
        this.items = new OrderItem[10]; // Fixed size array for simplicity
        this.itemCount = 0;
    }

    public String getId() {
        return id;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public Date getEstimatedCompletionDate() {
        return estimatedCompletionDate;
    }

    public void setEstimatedCompletionDate(Date estimatedCompletionDate) {
        this.estimatedCompletionDate = estimatedCompletionDate;
    }

    public Date getActualCompletionDate() {
        return actualCompletionDate;
    }

    public void setActualCompletionDate(Date actualCompletionDate) {
        this.actualCompletionDate = actualCompletionDate;
    }

    public String getVehicleRegistration() {
        return vehicleRegistration;
    }

    public void setVehicleRegistration(String vehicleRegistration) {
        this.vehicleRegistration = vehicleRegistration;
    }

    public Client getClient() {
        return client;
    }

    public OrderItem[] getItems() {
        return items;
    }

    /**
     * Adds an item to the service order.
     *
     * @param item the item to add
     */
    public void addItem(OrderItem item) {
        if (itemCount < items.length) {
            items[itemCount++] = item;
        } else {
            System.out.println("Item list is full");
        }
    }

    /**
     * Removes an item from the service order.
     *
     * @param item the item to remove
     */
    public void removeItem(OrderItem item) {
        for (int i = 0; i < itemCount; i++) {
            if (items[i].equals(item)) {
                items[i] = items[--itemCount];
                items[itemCount] = null;
                break;
            }
        }
    }
}
