package com.example.dsaproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;
import java.util.Optional;
import java.util.List;

/**
 * Controller for managing events on the events page.
 */
public class EventsPageController {

    @FXML
    private Button deleteEventsButton;

    @FXML
    private Button searchEventsbutton;

    @FXML
    private Button sortEvents;

    @FXML
    private ListView<String> eventsListView;

    private EventModel eventModel;

    /**
     * Initializes the controller by getting the singleton instance of EventModel.
     */
    @FXML
    public void initialize() {
        eventModel = EventModel.getInstance(); // Get the singleton instance
        //updateEventsListView();
    }

    /**
     * Handles the deletion of an event. Prompts the user for the event ID and attempts to remove the event with the given ID.
     *
     * @param event the action event that triggered this method
     */
    @FXML
    void deleteEvent(ActionEvent event) {
        // Create a dialog to get the ID of the event to be deleted
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Delete Event");
        dialog.setHeaderText("Enter Event ID");
        dialog.setContentText("Event ID:");

        // Show the dialog and get the user input
        String eventId = dialog.showAndWait().orElse(null);

        if (eventId != null && !eventId.trim().isEmpty()) {
            // Retrieve the central EventModel instance
            EventModel eventModel = EventModel.getInstance();
            EventList eventList = eventModel.getEventList();

            // Attempt to remove the event with the given ID
            boolean success = eventList.removeEventById(eventId);

            // Show an alert with the result of the deletion attempt
            Alert alert = new Alert(success ? AlertType.INFORMATION : AlertType.ERROR);
            alert.setTitle(success ? "Success" : "Failure");
            alert.setHeaderText(success ? "Event Deleted" : "Error Deleting Event");
            alert.setContentText(success ? "Event with ID " + eventId + " was successfully deleted." :
                    "Event with ID " + eventId + " could not be found.");
            alert.showAndWait();
        } else {
            // Show an error alert if the ID was not entered
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Input Error");
            alert.setHeaderText("No Event ID Entered");
            alert.setContentText("Please enter a valid Event ID.");
            alert.showAndWait();
        }
    }

    /**
     * Handles the search for events by title. Prompts the user for the event title and displays matching events.
     */
    @FXML
    void searchEvents() {
        // Create a TextInputDialog to prompt the user for the event title
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Search Events");
        dialog.setHeaderText("Enter the event title to search:");
        dialog.setContentText("Title:");

        // Show the dialog and capture the user input
        Optional<String> result = dialog.showAndWait();

        // Check if the user provided an input
        if (result.isPresent()) {
            String title = result.get().trim();

            // Retrieve the central EventModel instance
            EventModel eventModel = EventModel.getInstance();
            EventList eventList = eventModel.getEventList();

            // Search for events with the given title
            List<Event> matchingEvents = eventList.searchEventsByTitle(title);

            // Prepare the search results
            StringBuilder searchResults = new StringBuilder();
            if (matchingEvents.isEmpty()) {
                searchResults.append("No events found with the title: ").append(title);
            } else {
                for (Event event : matchingEvents) {
                    searchResults.append(event.toString()).append("\n\n");
                }
            }

            // Show an alert with the search results
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Search Results");
            alert.setHeaderText("Events Matching Title: " + title);
            alert.setContentText(searchResults.toString());
            alert.showAndWait();
        }
    }

    /**
     * Handles sorting and displaying events. Updates the ListView with events sorted by date and time.
     *
     * @param event the action event that triggered this method
     */
    @FXML
    void sortAndDisplayEvents(ActionEvent event) {
        updateEventsListView();
    }

    /**
     * Updates the ListView with events sorted by date and time.
     */
    private void updateEventsListView() {
        eventsListView.getItems().clear();
        for (Event event : eventModel.getEventList().getSortedEventsByDateAndTime()) {
            eventsListView.getItems().add(event.toString());
        }
    }
}
