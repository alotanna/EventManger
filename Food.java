package com.example.dsaproject;

/**
 * Represents a food item with a name and price.
 */
public class Food {
    private String name;
    private double price;

    /**
     * Constructs a Food object with the specified name and price.
     *
     * @param name  the name of the food item
     * @param price the price of the food item
     */
    public Food(String name, double price) {
        this.name = name;
        this.price = price;
    }

    /**
     * Returns the name of the food item.
     *
     * @return the name of the food item
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the food item.
     *
     * @param name the new name of the food item
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the price of the food item.
     *
     * @return the price of the food item
     */
    public double getPrice() {
        return price;
    }

    /**
     * Sets the price of the food item. The price must be non-negative.
     *
     * @param price the new price of the food item
     */
    public void setPrice(double price) {
        if (price >= 0) {
            this.price = price;
        } else {
            System.out.println("Price cannot be negative.");
        }
    }

    /**
     * Returns a string representation of the food item.
     * The format is: "name - $price", where price is formatted to two decimal places.
     *
     * @return a string representation of the food item
     */
    @Override
    public String toString() {
        return name + " - $" + String.format("%.2f", price);
    }
}
