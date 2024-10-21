package com.example.dsaproject;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Manages a collection of events, providing methods to add, remove, search, and sort events.
 */
public class EventList {
    private Map<String, Event> events; // Use a HashMap to store events by their ID

    /**
     * Constructs an empty EventList.
     */
    public EventList() {
        this.events = new HashMap<>();
    }

    /**
     * Adds an event to the list.
     *
     * @param event the event to add
     * @return true if the event was added successfully, false if an event with the same ID already exists
     */
    public boolean addEvent(Event event) {
        if (!events.containsKey(event.getEventId())) {
            events.put(event.getEventId(), event);
            return true;
        } else {
            System.out.println("Event with this ID already exists.");
            return false;
        }
    }

    /**
     * Removes an event from the list by event object.
     *
     * @param event the event to remove
     */
    public void removeEvent(Event event) {
        if (events.containsKey(event.getEventId())) {
            events.remove(event.getEventId());
        } else {
            System.out.println("Event not found in the list.");
        }
    }

    /**
     * Removes an event from the list by its ID.
     *
     * @param eventId the ID of the event to remove
     * @return true if the event was removed successfully, false if no event with the given ID was found
     */
    public boolean removeEventById(String eventId) {
        if (events.containsKey(eventId)) {
            events.remove(eventId);
            return true;
        } else {
            System.out.println("Event with ID " + eventId + " not found.");
            return false;
        }
    }

    /**
     * Removes events from the list by title.
     *
     * @param title the title of the events to remove
     */
    public void removeEventsByTitle(String title) {
        Iterator<Map.Entry<String, Event>> iterator = events.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Event> entry = iterator.next();
            if (entry.getValue().getTitle().equalsIgnoreCase(title)) {
                iterator.remove();
            }
        }
    }

    /**
     * Returns a sorted list of events by date and time.
     *
     * @return a list of events sorted by date and start time
     */
    public List<Event> getSortedEventsByDateAndTime() {
        List<Event> sortedEvents = new ArrayList<>(events.values());
        sortedEvents.sort(Comparator.comparing(Event::getDate)
                .thenComparing(Event::getStartTime));
        return sortedEvents;
    }

    /**
     * Returns a sorted list of events by month.
     *
     * @return a list of events sorted by year, month, date, and start time
     */
    public List<Event> getSortedEventsByMonth() {
        return events.values().stream()
                .sorted(Comparator.comparing((Event event) -> event.getDate().getYear())
                        .thenComparing(event -> event.getDate().getMonthValue())
                        .thenComparing(Event::getDate)
                        .thenComparing(Event::getStartTime))
                .collect(Collectors.toList());
    }

    /**
     * Returns a sorted list of events by year.
     *
     * @return a list of events sorted by year, date, and start time
     */
    public List<Event> getSortedEventsByYear() {
        return events.values().stream()
                .sorted(Comparator.comparing((Event event) -> event.getDate().getYear())
                        .thenComparing(Event::getDate)
                        .thenComparing(Event::getStartTime))
                .collect(Collectors.toList());
    }

    /**
     * Searches for events by title.
     *
     * @param title the title of the events to search for
     * @return a list of events with the specified title
     */
    public List<Event> searchEventsByTitle(String title) {
        List<Event> results = new ArrayList<>();
        for (Event event : events.values()) {
            if (event.getTitle().equalsIgnoreCase(title)) {
                results.add(event);
            }
        }
        return results;
    }

    /**
     * Retrieves an event by its ID.
     *
     * @param eventId the ID of the event to retrieve
     * @return the event with the specified ID, or null if no event with the ID is found
     */
    public Event getEventById(String eventId) {
        return events.get(eventId);
    }

    /**
     * Prints events that occur between the specified start and end dates.
     *
     * @param startDate the start date for the range
     * @param endDate   the end date for the range
     */
    public void printEventsBetweenDates(LocalDate startDate, LocalDate endDate) {
        for (Event event : events.values()) {
            if ((event.getDate().isEqual(startDate) || event.getDate().isAfter(startDate)) &&
                    (event.getDate().isEqual(endDate) || event.getDate().isBefore(endDate))) {
                System.out.println(event);
            }
        }
    }

    /**
     * Displays all events in the list sorted by date and time.
     */
    public void displayAllEvents() {
        for (Event event : getSortedEventsByDateAndTime()) {
            System.out.println(event);
        }
    }

    /**
     * Returns the number of events in the list.
     * @return the number of events
     */
    public int getSize() {
        return events.size();
    }
}
