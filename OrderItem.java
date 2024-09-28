package com.carsolutions;
/*
 * Name: <Buğra TUTUMLU>
 * Number: <8230800>
 * Class: <LEI_PP>
 */
/**
 * Represents an item in a service order.
 */
public abstract class OrderItem {
    private String description;

    /**
     * Constructs a new OrderItem with the specified description.
     *
     * @param description the description of the item
     */
    public OrderItem(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
