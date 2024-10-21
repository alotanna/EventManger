package com.example.dsaproject;

import java.util.ArrayList;

/**
 * Represents a menu containing a list of food items.
 * Provides methods to add, remove, and check food items, as well as display the menu.
 */
public class Menu {
    private ArrayList<Food> foodItems;

    /**
     * Constructs an empty menu.
     */
    public Menu() {
        this.foodItems = new ArrayList<>();
    }

    /**
     * Adds a food item to the menu.
     *
     * @param food the food item to add
     */
    public void addFood(Food food) {
        foodItems.add(food);
    }

    /**
     * Removes a food item from the menu by its name.
     * Returns the removed food item if found, otherwise returns null.
     *
     * @param name the name of the food item to remove
     * @return the removed food item, or null if no item with the given name was found
     */
    public Food removeFood(String name) {
        for (int i = 0; i < foodItems.size(); i++) {
            Food food = foodItems.get(i);
            if (food.getName().equalsIgnoreCase(name)) {
                foodItems.remove(i);
                return food;
            }
        }
        return null; // Return null if no food item was found
    }

    /**
     * Checks if the menu is empty (i.e., contains no food items).
     *
     * @return true if the menu is empty, false otherwise
     */
    public boolean isEmpty() {
        return foodItems.isEmpty();
    }

    /**
     * Displays all food items in the menu.
     */
    public void displayMenu() {
        System.out.println("=== Menu ===");
        for (Food food : foodItems) {
            System.out.println(food);
        }
    }

    /**
     * Gets the list of food items in the menu.
     *
     * @return the list of food items
     */
    public ArrayList<Food> getFoodItems() {
        return foodItems;
    }

    /**
     * Checks if a food item is available on the menu by its name.
     * Uses Java Streams to filter the list of food items based on the given name.
     *
     * @param name the name of the food item to check
     * @return true if the food item is available, false otherwise
     */
    public boolean isFoodAvailable(String name) {
        // Create a stream from the list of food items
        return foodItems.stream()
                // Filter the stream to match the given food name, case insensitive
                .anyMatch(food -> food.getName().equalsIgnoreCase(name));
        // Returns true if any food item matches the given name, otherwise returns false
    }
}
