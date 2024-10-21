package com.example.dsaproject;

public class Activity {
    // The name of the activity
    private String name;

    // The description of the activity
    private String description;

    // Constructor
    /**
     * Creates an Activity with the specified name and description
     * @param name The name of the activity.
     * @param description The description of the activity.
     */
    public Activity(String name, String description) {
        this.name = name;
        this.description = description;
    }

    // Getters and Setters
    /**
     * Gets the name of the activity.
     * @return The name of the activity.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the activity.
     * @param name The new name of the activity.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the description of the activity.
     * @return The description of the activity.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the activity.
     * @param description The new description of the activity.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Displays the details of the activity on the console.
     * This includes the name and description of the activity.
     */
    public void displayDetails() {
        System.out.println("Activity Name: " + name);
        System.out.println("Description: " + description);
    }


    /**
     * Returns a string representation of the activity.
     * @return A string containing the name and description of the activity.
     */
    @Override
    public String toString() {
        return "Activity Name: " + name + ",\n Description: " + description;
    }
}
