package com.example.dsaproject;

import java.time.LocalDate;

public class Bill {
    private String billId; // Unique identifier for the bill
    private String description; // Description of the bill
    private double amount; // Amount of the bill
    private LocalDate dueDate; // Due date for the bill

    /**
     * Constructs a Bill with the specified details.
     *
     * @param billId The unique identifier for the bill.
     * @param description The description of the bill.
     * @param amount The amount of the bill.
     * @param dueDate The due date for the bill.
     */
    public Bill(String billId, String description, double amount, LocalDate dueDate) {
        this.billId = billId; // Initialize the bill ID
        this.description = description; // Initialize the description
        this.amount = amount; // Initialize the amount
        this.dueDate = dueDate; // Initialize the due date
    }

    /**
     * Returns the unique identifier of the bill.
     *
     * @return The bill ID.
     */
    public String getBillId() {
        return billId;
    }

    /**
     * Sets the unique identifier for the bill.
     *
     * @param billId The bill ID to be set.
     */
    public void setBillId(String billId) {
        this.billId = billId;
    }

    /**
     * Returns the description of the bill.
     *
     * @return The description of the bill.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the bill.
     *
     * @param description The description to be set.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Returns the amount of the bill.
     *
     * @return The amount of the bill.
     */
    public double getAmount() {
        return amount;
    }

    /**
     * Sets the amount of the bill. Ensures that the amount is not negative.
     *
     * @param amount The amount to be set.
     */
    public void setAmount(double amount) {
        if (amount >= 0) {
            this.amount = amount; // Set the amount if it's non-negative
        } else {
            System.out.println("Amount cannot be negative."); // Print error message if amount is negative
        }
    }

    /**
     * Returns the due date of the bill.
     *
     * @return The due date of the bill.
     */
    public LocalDate getDueDate() {
        return dueDate;
    }

    /**
     * Sets the due date for the bill.
     *
     * @param dueDate The due date to be set.
     */
    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    /**
     * Displays the details of the bill.
     */
    public void displayDetails() {
        System.out.println("Bill ID: " + billId);
        System.out.println("Description: " + description);
        System.out.println("Amount: $" + String.format("%.2f", amount));
        System.out.println("Due Date: " + dueDate);
    }

    /**
     * Returns a string representation of the bill.
     *
     * @return A string representation of the bill.
     */
    @Override
    public String toString() {
        return "Bill ID: " + billId + ", Description: " + description + ", Amount: $" + String.format("%.2f", amount) + ", Due Date: " + dueDate;
    }
}
