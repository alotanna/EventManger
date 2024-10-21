package com.example.dsaproject;

import java.util.ArrayList;

public class PersonList {
    private ArrayList<Person> people;

    // Constructor to initialize the list of people
    public PersonList() {
        this.people = new ArrayList<>();
    }

    /**
     * Adds a person to the list.
     *
     * @param person The person to be added to the list.
     */
    public void addPerson(Person person) {
        if (person != null) {
            people.add(person);
        } else {
            System.out.println("Cannot add a null person to the list.");
        }
    }

    /**
     * Removes a person from the list by index.
     *
     * @param index The index of the person to be removed.
     */
    public void removePerson(int index) {
        if (index >= 0 && index < people.size()) {
            people.remove(index);
        } else {
            System.out.println("Index out of bounds. Valid index range is 0 to " + (people.size() - 1) + ".");
        }
    }

    /**
     * Removes a person from the list by the person object.
     *
     * @param person The person object to be removed.
     */
    public void removePerson(Person person) {
        if (people.contains(person)) {
            people.remove(person);
        } else {
            System.out.println("Person not found in the list.");
        }
    }

    /**
     * Retrieves a person from the list by index.
     *
     * @param index The index of the person to retrieve.
     * @return The person at the specified index, or null if the index is out of bounds.
     */
    public Person getPerson(int index) {
        if (index >= 0 && index < people.size()) {
            return people.get(index);
        } else {
            System.out.println("Index out of bounds. Valid index range is 0 to " + (people.size() - 1) + ".");
            return null;
        }
    }

    /**
     * Displays all people in the list.
     */
    public void displayAllPeople() {
        for (Person person : people) {
            person.displayInfo();
            System.out.println(); // For better readability between entries
        }
    }

    /**
     * Gets the total number of people in the list.
     *
     * @return The number of people in the list.
     */
    public int getSize() {
        return people.size();
    }

    /**
     * Checks if the list contains a person by name.
     *
     * @param name The name of the person to search for.
     * @return true if a person with the specified name is in the list, false otherwise.
     */
    public boolean containsPersonByName(String name) {
        if (name == null || name.isEmpty()) {
            System.out.println("Name must be provided.");
            return false;
        }
        return people.stream().anyMatch(person -> person.getName().equalsIgnoreCase(name));
    }
}
