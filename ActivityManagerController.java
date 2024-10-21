package com.example.dsaproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.TextInputDialog;

public class ActivityManagerController {

    @FXML
    private TextField activityDescription; // TextField for activity description

    @FXML
    private TextField activityname; // TextField for activity name

    @FXML
    private Button deleteActivity; // Button to delete an activity

    @FXML
    private TextField eventID; // TextField for event ID

    @FXML
    private Button saveandExit; // Button to save activity and exit

    @FXML
    private Button viewActivity; // Button to view activities

    /**
     * Handles the action to save an activity and exit.
     * Checks if all fields are filled and adds the activity to the specified event.
     * Shows an alert based on the success or failure of the operation.
     * @param event The action event triggered by clicking the save and exit button.
     */
    @FXML
    void saveAndExit(ActionEvent event) {
        String eventId = eventID.getText(); // Retrieve event ID from the text field
        String name = activityname.getText(); // Retrieve activity name from the text field
        String description = activityDescription.getText(); // Retrieve activity description from the text field

        if (eventId.isEmpty() || name.isEmpty() || description.isEmpty()) {
            // Show error alert if any field is empty
            showAlert(AlertType.ERROR, "Error", "All input fields must be filled.");
            return;
        }

        EventModel model = EventModel.getInstance(); // Get the instance of EventModel
        EventList eventList = model.getEventList(); // Retrieve the list of events
        Event existingEvent = eventList.getEventById(eventId); // Get the event by ID

        if (existingEvent == null) {
            // Show error alert if event with the specified ID is not found
            showAlert(AlertType.ERROR, "Error", "Event with the specified ID not found.");
            return;
        }

        Activity activity = new Activity(name, description); // Create a new Activity object
        existingEvent.addActivity(activity); // Add the activity to the event

        // Show success alert and clear input fields
        showAlert(AlertType.INFORMATION, "Success", "Activity added successfully.");
        clearFields();
    }

    /**
     * Handles the action to view activities for a specific event.
     * Prompts the user to enter an event ID and displays activities for that event.
     *
     * @param event The action event triggered by clicking the view activity button.
     */
    @FXML
    void viewActivity(ActionEvent event) {
        TextInputDialog dialog = new TextInputDialog(); // Create a dialog to get event ID from the user
        dialog.setTitle("View Activities");
        dialog.setHeaderText("Enter Event ID:");
        dialog.setContentText("Event ID:");

        String eventId = dialog.showAndWait().orElse(""); // Get event ID from the dialog

        EventModel model = EventModel.getInstance(); // Get the instance of EventModel
        EventList eventList = model.getEventList(); // Retrieve the list of events
        Event existingEvent = eventList.getEventById(eventId); // Get the event by ID

        if (existingEvent == null) {
            // Show error alert if event with the specified ID is not found
            showAlert(AlertType.ERROR, "Error", "Event with the specified ID not found.");
            return;
        }

        String activities = existingEvent.displayAllActivities(); // Get the activities as a string
        // Show an alert with the activities details
        showAlert(AlertType.INFORMATION, "Activities", "Activities for Event ID " + eventId + ":\n" + activities);
    }

    /**
     * Handles the action to delete an activity from a specific event.
     * Prompts the user to enter an event ID and deletes an activity from that event.
     *
     * @param event The action event triggered by clicking the delete activity button.
     */
    @FXML
    void deleteActivity(ActionEvent event) {
        TextInputDialog dialog = new TextInputDialog(); // Create a dialog to get event ID from the user
        dialog.setTitle("Delete Activity");
        dialog.setHeaderText("Enter Event ID:");
        dialog.setContentText("Event ID:");

        String eventId = dialog.showAndWait().orElse(""); // Get event ID from the dialog

        EventModel model = EventModel.getInstance(); // Get the instance of EventModel
        EventList eventList = model.getEventList(); // Retrieve the list of events
        Event existingEvent = eventList.getEventById(eventId); // Get the event by ID

        if (existingEvent == null) {
            // Show error alert if event with the specified ID is not found
            showAlert(AlertType.ERROR, "Error", "Event with the specified ID not found.");
            return;
        }

        Activity act = existingEvent.removeActivity(); // Remove an activity from the event
        if (act != null) {
            // Show success alert if activity is removed successfully
            showAlert(AlertType.INFORMATION, "Success", act.toString() + "Activity deleted successfully.");
        } else {
            // Show error alert if activity could not be removed
            showAlert(AlertType.ERROR, "Error", "Failed to delete activity.");
        }
    }

    /**
     * Displays an alert with the specified type, title, and message.
     * @param type The type of alert
     * @param title The title of the alert.
     * @param message The content of the alert.
     */
    private void showAlert(AlertType type, String title, String message) {
        Alert alert = new Alert(type); // Create an alert of the specified type
        alert.setTitle(title); // Set the title of the alert
        alert.setHeaderText(null); // No header text
        alert.setContentText(message); // Set the content of the alert
        alert.showAndWait(); // Display the alert and wait for user response
    }

    /**
     * Clears all input fields in the form.
     */
    private void clearFields() {
        eventID.clear(); // Clear the event ID field
        activityname.clear(); // Clear the activity name field
        activityDescription.clear(); // Clear the activity description field
    }
}
