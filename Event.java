package com.example.dsaproject;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

/**
 * Represents an event with various attributes including details about guests, employees, activities, bills, venue, and menu.
 */
public class Event {
    private String eventId;
    private String title;
    private String description;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private PersonList guests; // Using PersonList for guests
    private PersonList employees; // Using PersonList for employees
    private ActivityList activities;
    private BillList bills;
    private Venue venue; // Single venue
    private Menu menu;

    /**
     * Constructs a new Event with the specified details.
     *
     * @param eventId      the unique identifier of the event
     * @param title        the title of the event
     * @param description  a brief description of the event
     * @param date         the date of the event
     * @param startTime    the start time of the event
     * @param endTime      the end time of the event
     */
    public Event(String eventId, String title, String description, LocalDate date, LocalTime startTime, LocalTime endTime) {
        this.eventId = eventId;
        this.title = title;
        this.description = description;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.guests = new PersonList(); // Initialize PersonList for guests
        this.employees = new PersonList(); // Initialize PersonList for employees
        this.activities = new ActivityList();
        this.bills = new BillList();
        this.venue = null; // No venue initially
        this.menu = new Menu();
    }

    // Getters and Setters

    /**
     * Returns the unique identifier of the event.
     *
     * @return the event ID
     */
    public String getEventId() {
        return eventId;
    }

    /**
     * Sets the unique identifier of the event.
     *
     * @param eventId the event ID
     */
    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    /**
     * Returns the title of the event.
     *
     * @return the event title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the event.
     *
     * @param title the event title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Returns the description of the event.
     *
     * @return the event description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the event.
     *
     * @param description the event description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Returns the date of the event.
     *
     * @return the event date
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Sets the date of the event.
     *
     * @param date the event date
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }

    /**
     * Returns the start time of the event.
     *
     * @return the event start time
     */
    public LocalTime getStartTime() {
        return startTime;
    }

    /**
     * Sets the start time of the event.
     *
     * @param startTime the event start time
     */
    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    /**
     * Returns the end time of the event.
     *
     * @return the event end time
     */
    public LocalTime getEndTime() {
        return endTime;
    }

    /**
     * Sets the end time of the event.
     *
     * @param endTime the event end time
     */
    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    // Guests Management

    /**
     * Adds a guest to the event.
     *
     * @param guest the guest to add
     */
    public void addGuest(Guest guest) {
        guests.addPerson(guest);
    }

    /**
     * Removes a guest from the event.
     *
     * @param guest the guest to remove
     */
    public void removeGuest(Guest guest) {
        guests.removePerson(guest);
    }

    /**
     * Returns the list of guests for the event.
     *
     * @return the list of guests
     */
    public PersonList getGuests() {
        return guests;
    }

    // Employees Management

    /**
     * Adds an employee to the event.
     *
     * @param employee the employee to add
     */
    public void addEmployee(Employee employee) {
        employees.addPerson(employee);
    }

    /**
     * Removes an employee from the event.
     *
     * @param employee the employee to remove
     */
    public void removeEmployee(Employee employee) {
        employees.removePerson(employee);
    }

    /**
     * Returns the list of employees for the event.
     *
     * @return the list of employees
     */
    public PersonList getEmployees() {
        return employees;
    }

    // Activities Management

    /**
     * Adds an activity to the event.
     *
     * @param activity the activity to add
     */
    public void addActivity(Activity activity) {
        activities.enqueue(activity);
    }

    /**
     * Removes and returns the next activity from the event.
     *
     * @return the removed activity, or null if no activities are available
     */
    public Activity removeActivity() {
        if (activities.dequeue() == null) {
            return null;
        }
        return activities.dequeue();
    }

    /**
     * Returns the next activity in the event.
     *
     * @return the next activity, or null if no activities are available
     */
    public Activity getNextActivity() {
        return activities.peek();
    }

    /**
     * Returns a string representation of all activities in the event.
     *
     * @return a string containing details of all activities
     */
    public String displayAllActivities() {
        return activities.getAllActivitiesDetails();
    }

    // Bills Management

    /**
     * Adds a bill to the event.
     *
     * @param bill the bill to add
     */
    public void addBill(Bill bill) {
        bills.addBill(bill);
    }

    /**
     * Removes a bill from the event by its ID.
     *
     * @param billId the ID of the bill to remove
     * @return the removed bill, or null if no bill with the specified ID is found
     */
    public Bill removeBill(String billId) {
        return bills.removeBill(billId);
    }

    /**
     * Retrieves a bill by its ID.
     *
     * @param billId the ID of the bill to retrieve
     * @return the bill with the specified ID, or null if no bill with the ID is found
     */
    public Bill getBill(String billId) {
        return bills.getBillById(billId);
    }

    /**
     * Returns a string representation of all bills in the event.
     *
     * @return a string containing details of all bills
     */
    public String listAllBills() {
        return bills.listAllBills();
    }

    // Venue Management

    /**
     * Sets the venue for the event.
     *
     * @param venue the venue to set
     */
    public void setVenue(Venue venue) {
        this.venue = venue;
    }

    /**
     * Returns the venue for the event.
     *
     * @return the venue, or null if no venue is set
     */
    public Venue getVenue() {
        return venue;
    }

    // Menu Management

    /**
     * Adds a food item to the event's menu.
     *
     * @param food the food item to add
     */
    public void addFoodItem(Food food) {
        menu.addFood(food);
    }

    /**
     * Removes a food item from the event's menu by its name.
     *
     * @param name the name of the food item to remove
     * @return the removed food item, or null if no food item with the specified name is found
     */
    public Food removeFoodItem(String name) {
        return menu.removeFood(name);
    }

    /**
     * Returns a list of all food items in the event's menu.
     *
     * @return an ArrayList containing all food items
     */
    public ArrayList<Food> getMenuItems() {
        return menu.getFoodItems();
    }

    /**
     * Displays the menu for the event.
     */
    public void displayMenu() {
        menu.displayMenu();
    }

    /**
     * Returns a string representation of the event's details.
     *
     * @return a string containing all details of the event
     */
    @Override
    public String toString() {
        // Start building the event details string
        StringBuilder eventDetails = new StringBuilder();

        // Add event basic information
        eventDetails.append("Event ID: ").append(eventId).append("\n")
                .append("Title: ").append(title).append("\n")
                .append("Description: ").append(description).append("\n")
                .append("Date: ").append(date).append("\n")
                .append("Start Time: ").append(startTime).append("\n")
                .append("End Time: ").append(endTime).append("\n");

        // Add venue details
        if (venue == null) {
            eventDetails.append("Venue: Unspecified\n");
        } else {
            eventDetails.append("Venue:\n").append(venue.toString()).append("\n");
        }

        // Add activities details
        if (activities.isEmpty()) {
            eventDetails.append("Activities: No activities scheduled\n");
        } else {
            eventDetails.append("Activities:\n").append(displayAllActivities()).append("\n");
        }

        // Add menu details
        if (menu.isEmpty()) {
            eventDetails.append("Menu: No menu items available\n");
        } else {
            eventDetails.append("Menu:\n");
            for (Food food : getMenuItems()) {
                eventDetails.append(food.toString()).append("\n");
            }
        }

        if (bills.listAllBills() == null) {
            eventDetails.append("Bills: No bills available\n");
        } else {
            eventDetails.append(listAllBills());
        }

        // Combine all details into a single string
        return eventDetails.toString();
    }
}
