package com.carsolutions;
/*
 * Name: <Buğra TUTUMLU>
 * Number: <8230800>
 * Class: <LEI_PP>
 */
/**
 * Represents a part item in a service order.
 */
public class Part extends OrderItem {
    private int quantity;

    /**
     * Constructs a new Part with the specified description and quantity.
     *
     * @param description the description of the part
     * @param quantity the quantity of the part
     */
    public Part(String description, int quantity) {
        super(description);
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }
}
