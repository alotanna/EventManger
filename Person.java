package com.example.dsaproject;

public class Person {
    private String name;
    private int age;
    private String email;

    /**
     * Constructor to initialize a Person object with name, age, and email.
     *
     * @param name  The name of the person.
     * @param age   The age of the person.
     * @param email The email address of the person.
     */
    public Person(String name, int age, String email) {
        this.name = name;
        this.age = age;
        this.email = email;
    }

    // Getters and Setters

    /**
     * Gets the name of the person.
     *
     * @return The name of the person.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the person.
     *
     * @param name The new name of the person.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the age of the person.
     *
     * @return The age of the person.
     */
    public int getAge() {
        return age;
    }

    /**
     * Sets the age of the person. Age must be positive.
     *
     * @param age The new age of the person.
     */
    public void setAge(int age) {
        if (age > 0) {
            this.age = age;
        } else {
            System.out.println("Age must be positive.");
        }
    }

    /**
     * Gets the email address of the person.
     *
     * @return The email address of the person.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email address of the person.
     *
     * @param email The new email address of the person.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Displays the person's details including name, age, and email.
     */
    public void displayInfo() {
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Email: " + email);
    }

    /**
     * Checks if the person is an adult (18 years or older).
     *
     * @return true if the person is an adult, false otherwise.
     */
    public boolean isAdult() {
        return age >= 18;
    }

    /**
     * Returns a string representation of the Person object.
     *
     * @return A string containing the name, age, and email of the person.
     */
    @Override
    public String toString() {
        return "Person [Name=" + name + ", Age=" + age + ", Email=" + email + "]";
    }
}
