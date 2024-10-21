package com.example.dsaproject;

/**
 * Singleton class to manage a single instance of EventList.
 */
public class EventModel {
    private static EventModel instance;
    private EventList eventList;

    /**
     * Private constructor to prevent instantiation from outside the class.
     */
    private EventModel() {
        eventList = new EventList(); // Initialize the event list
    }

    /**
     * Returns the singleton instance of EventModel.
     * If the instance does not exist, it creates one.
     *
     * @return the singleton instance of EventModel
     */
    public static synchronized EventModel getInstance() {
        if (instance == null) {
            instance = new EventModel();
        }
        return instance;
    }

    /**
     * Gets the EventList managed by this EventModel.
     *
     * @return the EventList instance
     */
    public EventList getEventList() {
        return eventList;
    }
}
