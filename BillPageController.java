package com.example.dsaproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Window;

/**
 * Controller for the Bill Page in the application.
 * Manages the interactions and actions related to bill management including adding, removing, and viewing bills.
 */
public class BillPageController {

    @FXML
    private Spinner<Double> Amount;

    @FXML
    private TextField BillDescription;

    @FXML
    private TextField BillID;

    @FXML
    private DatePicker DueDate;

    @FXML
    private TextField eventID;

    @FXML
    private Button removeBill;

    @FXML
    private Button saveandExit;

    @FXML
    private Button viewBills;

    /**
     * Initializes the controller and sets up the spinner for amount input.
     */
    @FXML
    void initialize() {
        // Set up the spinner for amount with a range from 0.0 to 1000000.0 and a step of 0.1
        SpinnerValueFactory<Double> valueFactory = new SpinnerValueFactory.DoubleSpinnerValueFactory(0.0, 1000000.0, 0.0, 0.1);
        Amount.setValueFactory(valueFactory);
    }

    /**
     * Handles the action of removing a bill.
     * Prompts the user for the event ID and bill ID, then removes the bill from the list.
     * Displays appropriate error and success messages.
     *
     * @param curevent the ActionEvent triggered by the removeBill button
     */
    @FXML
    void removeABill(ActionEvent curevent) {
        // Create and show the dialog for bill removal
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Remove Bill");
        dialog.setHeaderText("Enter Event ID:");
        dialog.setContentText("Event ID:");

        String eventId = dialog.showAndWait().orElse("");
        if (eventId.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Input Error", "Event ID must be provided.");
            return;
        }

        // Retrieve the event and remove the bill
        EventModel model = EventModel.getInstance();
        EventList eventList = model.getEventList();
        Event event = eventList.getEventById(eventId);

        if (event == null) {
            showAlert(Alert.AlertType.ERROR, "Event Not Found", "No event found with the provided ID.");
            return;
        }

        dialog.setHeaderText("Enter Bill ID:");
        dialog.setContentText("Bill ID:");

        String billId = dialog.showAndWait().orElse("");
        if (billId.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Input Error", "Bill ID must be provided.");
            return;
        }

        // Remove the bill and show appropriate message
        Bill removed = event.removeBill(billId);
        if (removed != null) {
            showAlert(Alert.AlertType.INFORMATION, "Success", "Bill has been successfully removed.");
        } else {
            showAlert(Alert.AlertType.ERROR, "Removal Error", "Bill not found for the provided ID.");
        }
    }

    /**
     * Handles the action of saving a new bill.
     * Validates input, creates a new bill object, and adds it to the event's list of bills.
     * Displays appropriate error and success messages.
     *
     * @param curevent the ActionEvent triggered by the saveandExit button
     */
    @FXML
    void saveAndExit(ActionEvent curevent) {
        String eventId = eventID.getText().trim();
        String description = BillDescription.getText().trim();
        Double amount = Amount.getValue();
        java.time.LocalDate dueDate = DueDate.getValue();

        // Validate inputs
        if (eventId.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Input Error", "Event ID must be provided.");
            return;
        }

        if (description.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Input Error", "Bill description must be provided.");
            return;
        }

        if (amount == null || amount < 0) {
            showAlert(Alert.AlertType.ERROR, "Input Error", "Amount must be a positive number.");
            return;
        }

        if (dueDate == null) {
            showAlert(Alert.AlertType.ERROR, "Input Error", "Due date must be provided.");
            return;
        }

        // Create a new Bill object and add it to the event
        Bill newBill = new Bill(BillID.getText().trim(), description, amount, dueDate);
        EventModel model = EventModel.getInstance();
        EventList eventList = model.getEventList();
        Event event = eventList.getEventById(eventId);

        if (event == null) {
            showAlert(Alert.AlertType.ERROR, "Event Not Found", "No event found with the provided ID.");
            return;
        }

        event.addBill(newBill);
        showAlert(Alert.AlertType.INFORMATION, "Success", "Bill has been successfully added.");
    }

    /**
     * Handles the action of viewing bills for a specific event.
     * Shows a dialog to enter the event ID and displays all bills for that event.
     * Displays appropriate error messages if the event is not found or has no bills.
     *
     * @param curevent the ActionEvent triggered by the viewBills button
     */
    @FXML
    void viewEventBills(ActionEvent curevent) {
        // Create and show the dialog for viewing bills
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("View Bills");
        dialog.setHeaderText("Enter Event ID:");
        dialog.setContentText("Event ID:");

        String eventId = dialog.showAndWait().orElse("");
        if (eventId.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Input Error", "Event ID must be provided.");
            return;
        }

        // Retrieve the event and display its bills
        EventModel model = EventModel.getInstance();
        EventList eventList = model.getEventList();
        Event event = eventList.getEventById(eventId);

        if (event == null) {
            showAlert(Alert.AlertType.ERROR, "Event Not Found", "No event found with the provided ID.");
            return;
        }

        StringBuilder billsSummary = new StringBuilder();
        if (event.listAllBills() == null || event.listAllBills().isEmpty()) {
            billsSummary.append("No bills found for the provided event ID.");
        } else {
            billsSummary.append(event.listAllBills());
        }
        showAlert(Alert.AlertType.INFORMATION, "Bills Details", billsSummary.toString());
    }

    /**
     * Displays an alert dialog with the specified type, title, and content.
     *
     * @param type    the type of the alert (ERROR, INFORMATION, etc.)
     * @param title   the title of the alert
     * @param content the content of the alert
     */
    private void showAlert(Alert.AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
