package com.example.dsaproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ResourceBundle;

/**
 * Controller for the Create Events page in the application.
 * Manages the interactions and actions related to creating new events, including input validation and page navigation.
 */
public class CreateEventsController implements Initializable {

    @FXML
    private ComboBox<Integer> endHour;

    @FXML
    private ComboBox<Integer> endMinute;

    @FXML
    private TextField eventDescription;

    @FXML
    private TextField eventID;

    @FXML
    private DatePicker eventdate;

    @FXML
    private Button saveandExit;

    @FXML
    private ComboBox<Integer> startHour;

    @FXML
    private ComboBox<Integer> startMinute;

    @FXML
    private TextField title;

    /**
     * Initializes the controller and sets up the ComboBoxes for hours and minutes.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeTimeComboBoxes();
    }

    /**
     * Handles the action of saving a new event.
     * Validates input, creates a new Event object, and adds it to the event model.
     * Displays appropriate error and success messages and navigates to the ManageEventsPage.
     *
     * @param event the ActionEvent triggered by the saveandExit button
     */
    @FXML
    void saveAndExit(ActionEvent event) {
        try {
            // Gather input data
            String id = eventID.getText();
            String eventTitle = title.getText();
            String description = eventDescription.getText();
            LocalDate date = eventdate.getValue();
            Integer startHourValue = startHour.getValue();
            Integer startMinuteValue = startMinute.getValue();
            Integer endHourValue = endHour.getValue();
            Integer endMinuteValue = endMinute.getValue();

            // Check for null values
            if (id == null || eventTitle == null || date == null ||
                    startHourValue == null || startMinuteValue == null ||
                    endHourValue == null || endMinuteValue == null) {
                showAlert(Alert.AlertType.ERROR, "Input Error", "All fields must be filled.");
                return;
            }

            // Convert to LocalTime
            LocalTime startTime = LocalTime.of(startHourValue, startMinuteValue);
            LocalTime endTime = LocalTime.of(endHourValue, endMinuteValue);

            // Create the Event object
            Event newEvent = new Event(id, eventTitle, description, date, startTime, endTime);

            // Get the singleton EventModel instance and add the event
            EventModel eventModel = EventModel.getInstance();
            if (eventModel.getEventList().addEvent(newEvent)) {
                showAlert(Alert.AlertType.INFORMATION, "Success", "Event added successfully.");
            } else {
                showAlert(Alert.AlertType.ERROR, "Error", "Failed to add event.");
            }

            // Return to ManageEventsPage
            loadPage("ManageEventsPage.fxml");
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Exception", "An error occurred while saving the event.");
        }
    }

    /**
     * Displays an alert dialog with the specified type, title, and content.
     *
     * @param alertType the type of the alert (ERROR, INFORMATION, etc.)
     * @param title     the title of the alert
     * @param message   the content of the alert
     */
    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType, message, ButtonType.OK);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.showAndWait();
    }

    /**
     * Initializes the ComboBoxes for hours and minutes with appropriate values.
     * Sets default values for the ComboBoxes.
     */
    private void initializeTimeComboBoxes() {
        // Initialize hours from 0 to 23
        for (int i = 0; i < 24; i++) {
            startHour.getItems().add(i);
            endHour.getItems().add(i);
        }

        // Initialize minutes from 0 to 59
        for (int i = 0; i < 60; i++) {
            startMinute.getItems().add(i);
            endMinute.getItems().add(i);
        }

        // Set default values (optional)
        startHour.setValue(0);
        startMinute.setValue(0);
        endHour.setValue(0);
        endMinute.setValue(0);
    }

    /**
     * Loads a new FXML page and displays it.
     *
     * @param fxmlFileName the name of the FXML file to load
     */
    private void loadPage(String fxmlFileName) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFileName));
            AnchorPane newPane = loader.load();
            Stage stage = (Stage) saveandExit.getScene().getWindow();
            Scene scene = new Scene(newPane);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
