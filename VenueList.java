package com.example.dsaproject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Manages a collection of venues using a map for quick lookups.
 */
public class VenueList {
    private Map<String, Venue> venueMap; // Map to store venues with their ID as the key

    /**
     * Constructor to initialize the VenueList with an empty map.
     */
    public VenueList() {
        this.venueMap = new HashMap<>();
    }

    /**
     * Adds a venue to the list. If a venue with the same ID already exists, it will be replaced.
     *
     * @param venue The venue to add
     */
    public void addVenue(Venue venue) {
        venueMap.put(venue.getVenueId(), venue);
    }

    /**
     * Removes a venue from the list by its unique ID.
     *
     * @param venueId The ID of the venue to remove
     */
    public void removeVenue(String venueId) {
        venueMap.remove(venueId);
    }

    /**
     * Retrieves a venue by its unique ID.
     *
     * @param venueId The ID of the venue to retrieve
     * @return The venue with the specified ID, or null if not found
     */
    public Venue getVenue(String venueId) {
        return venueMap.get(venueId);
    }

    /**
     * Lists all venues in the map by displaying their details.
     */
    public void listAllVenues() {
        for (Venue venue : venueMap.values()) {
            venue.displayDetails(); // Display details for each venue
        }
    }

    /**
     * Gets a list of all available venues.
     *
     * @return A list of available venues
     */
    public List<Venue> getAvailableVenues() {
        List<Venue> availableVenues = new ArrayList<>();
        for (Venue venue : venueMap.values()) {
            if (venue.isAvailable()) { // Check if the venue is available
                availableVenues.add(venue); // Add to list if available
            }
        }
        return availableVenues;
    }
}
