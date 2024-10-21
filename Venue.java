package com.example.dsaproject;

/**
 * Represents a venue with its details.
 */
public class Venue {
    private String venueId;    // Unique identifier for the venue
    private String name;       // Name of the venue
    private String location;   // Location of the venue
    private int capacity;      // Capacity of the venue
    private boolean isAvailable; // Availability status of the venue

    /**
     * Constructor to initialize the venue with provided details.
     *
     * @param venueId   Unique identifier for the venue
     * @param name      Name of the venue
     * @param location  Location of the venue
     * @param capacity  Capacity of the venue
     */
    public Venue(String venueId, String name, String location, int capacity) {
        this.venueId = venueId;
        this.name = name;
        this.location = location;
        this.capacity = capacity;
        // Initial availability status is not set; defaults to false
        this.isAvailable = true; // Assuming a default value
    }

    // Getters and Setters

    /**
     * Gets the unique identifier for the venue.
     *
     * @return The venue ID
     */
    public String getVenueId() {
        return venueId;
    }

    /**
     * Sets the unique identifier for the venue.
     *
     * @param venueId The venue ID to set
     */
    public void setVenueId(String venueId) {
        this.venueId = venueId;
    }

    /**
     * Gets the name of the venue.
     *
     * @return The venue name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the venue.
     *
     * @param name The venue name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the location of the venue.
     *
     * @return The venue location
     */
    public String getLocation() {
        return location;
    }

    /**
     * Sets the location of the venue.
     *
     * @param location The venue location to set
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * Gets the capacity of the venue.
     *
     * @return The venue capacity
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * Sets the capacity of the venue. Capacity must be non-negative.
     *
     * @param capacity The venue capacity to set
     */
    public void setCapacity(int capacity) {
        if (capacity >= 0) {
            this.capacity = capacity;
        } else {
            System.out.println("Capacity cannot be negative."); // Error message if invalid capacity is provided
        }
    }

    /**
     * Checks if the venue is available.
     *
     * @return True if the venue is available, otherwise false
     */
    public boolean isAvailable() {
        return isAvailable;
    }

    /**
     * Sets the availability status of the venue.
     *
     * @param available The availability status to set
     */
    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    /**
     * Displays the details of the venue.
     */
    public void displayDetails() {
        System.out.println("Venue ID: " + venueId);
        System.out.println("Venue Name: " + name);
        System.out.println("Location: " + location);
        System.out.println("Capacity: " + capacity);
        System.out.println("Available: " + isAvailable);
    }

    /**
     * Returns a string representation of the venue.
     *
     * @return A string containing the venue details
     */
    @Override
    public String toString() {
        return "Venue ID: " + venueId + ",\nName: " + name + ",\nLocation: " + location + ",\nCapacity: " + capacity;
    }
}
