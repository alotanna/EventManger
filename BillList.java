package com.example.dsaproject;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents a collection of bills associated with an event.
 */
public class BillList {

    // A map to store bills with their IDs as keys
    private Map<String, Bill> billMap;

    /**
     * Constructs a new BillList.
     */
    public BillList() {
        this.billMap = new HashMap<>(); // Initialize the map for storing bills
    }

    /**
     * Adds a bill to the list.
     *
     * @param bill The bill to add.
     */
    public void addBill(Bill bill) {
        if (bill != null) {
            billMap.put(bill.getBillId(), bill); // Add the bill to the map using its ID as the key
        }
    }

    /**
     * Removes a bill from the list by its ID and returns the removed bill.
     *
     * @param billId The ID of the bill to remove.
     * @return The removed bill, or null if no bill with the specified ID is found.
     */
    public Bill removeBill(String billId) {
        return billMap.remove(billId); // Remove the bill from the map and return it
    }

    /**
     * Retrieves a bill by its ID.
     *
     * @param billId The ID of the bill to retrieve.
     * @return The bill with the specified ID, or null if no bill with the ID is found.
     */
    public Bill getBillById(String billId) {
        return billMap.get(billId); // Retrieve the bill from the map using its ID
    }

    /**
     * Lists all bills in the collection.
     *
     * @return A StringBuilder containing the details of all bills, or null if the collection is empty.
     */
    public String listAllBills() {
        if (billMap.isEmpty()) {
            return null; // Return null if there are no bills
        }
        StringBuilder sb = new StringBuilder();
        for (Bill bill : billMap.values()) {
            sb.append(bill.toString()).append("\n"); // Append each bill's details to the StringBuilder
        }
        return sb.toString(); // Return the concatenated string of all bills
    }
}
