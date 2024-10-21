package com.example.dsaproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.SpinnerValueFactory;

public class ModifyVenueController {

    @FXML
    private TextField VenueName;

    @FXML
    private Spinner<Integer> capacity;

    @FXML
    private TextField eventID;

    @FXML
    private Button eventvenue;

    @FXML
    private Button saveExit;

    @FXML
    private TextField venueID;

    @FXML
    private TextField venueLocation;

    /**
     * Initializes the controller by setting up the spinner for venue capacity.
     * The spinner is configured to accept integer values from 1 to 10000, with a default value of 1.
     */
    @FXML
    void initialize() {
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10000, 1);
        capacity.setValueFactory(valueFactory);
    }

    /**
     * Handles the action event when the user wants to view the venue details for a specific event.
     * Prompts the user to enter the event ID and then displays the venue details if the event exists
     * and has a venue assigned.
     *
     * @param event The action event triggered by the button click.
     */
    @FXML
    void Vieweventvenue(ActionEvent event) {
        // Prompt user to enter the event ID
        TextInputDialog eventIdDialog = new TextInputDialog();
        eventIdDialog.setTitle("View Venue");
        eventIdDialog.setHeaderText("Enter Event ID:");
        eventIdDialog.setContentText("Event ID:");

        // Get the event ID from user input
        String eventId = eventIdDialog.showAndWait().orElse("");

        // Check if the event ID is provided
        if (eventId.isEmpty()) {
            showAlert(AlertType.ERROR, "Input Error", "Event ID must be provided.");
            return;
        }

        // Retrieve the event from the model
        EventModel model = EventModel.getInstance();
        EventList eventList = model.getEventList();
        Event existingEvent = eventList.getEventById(eventId);

        // Check if the event exists and has an assigned venue
        if (existingEvent == null || existingEvent.getVenue() == null) {
            showAlert(AlertType.ERROR, "Event Not Found", "No event found with the provided ID or no venue assigned.");
            return;
        }

        // Display the venue details
        Venue venue = existingEvent.getVenue();
        showAlert(AlertType.INFORMATION, "Venue Details",
                "Venue ID: " + venue.getVenueId() +
                        "\nVenue Name: " + venue.getName() +
                        "\nLocation: " + venue.getLocation() +
                        "\nCapacity: " + venue.getCapacity());
    }

    /**
     * Handles the action event when the user wants to save and assign a venue to an event.
     * Validates the input fields, creates a new venue, assigns it to the specified event,
     * and displays a success message.
     *
     * @param event The action event triggered by the button click.
     */
    @FXML
    void saveAndExit(ActionEvent event) {
        // Retrieve and trim input values from text fields and spinner
        String id = venueID.getText().trim();
        String name = VenueName.getText().trim();
        String location = venueLocation.getText().trim();
        Integer cap = capacity.getValue(); // Get the capacity from the spinner

        // Validate inputs to ensure all fields are filled and capacity is a positive integer
        if (id.isEmpty() || name.isEmpty() || location.isEmpty() || cap == null || cap <= 0) {
            showAlert(AlertType.ERROR, "Input Error", "All fields must be filled and capacity must be a positive integer.");
            return;
        }

        // Retrieve the event from the model based on the provided event ID
        EventModel model = EventModel.getInstance();
        EventList eventList = model.getEventList();
        Event existingEvent = eventList.getEventById(eventID.getText().trim());

        // Check if the event exists
        if (existingEvent == null) {
            showAlert(AlertType.ERROR, "Event Not Found", "No event found with the provided ID.");
            return;
        }

        // Create a new Venue object and assign it to the event
        Venue venue = new Venue(id, name, location, cap); // Create a new Venue instance
        existingEvent.setVenue(venue);

        // Show success message to the user
        showAlert(AlertType.INFORMATION, "Success", "Venue has been successfully saved and linked to the event.");

        // Clear the input fields and reset spinner value
        clearFields();
    }

    /**
     * Clears all input fields and resets the spinner to its default value.
     */
    private void clearFields() {
        venueID.clear();
        VenueName.clear();
        venueLocation.clear();
        capacity.getValueFactory().setValue(1); // Reset spinner to its default value
        eventID.clear();
    }

    /**
     * Displays an alert dialog with the specified type, title, and message.
     *
     * @param type    The type of the alert (e.g., ERROR, INFORMATION).
     * @param title   The title of the alert dialog.
     * @param message The message to be displayed in the alert dialog.
     */
    private void showAlert(AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
