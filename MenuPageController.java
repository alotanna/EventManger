package com.example.dsaproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.stage.Window;

import java.util.List;

public class MenuPageController {

    @FXML
    private Button Viewmenu;

    @FXML
    private TextField eventID;

    @FXML
    private TextField foodName;

    @FXML
    private Spinner<Double> foodPrice;

    @FXML
    private Button removefood;

    @FXML
    private Button saveandExit;

    /**
     * Initializes the controller and sets up the spinner for food price.
     * The spinner allows price values from 0.0 to 1000.0 with a step of 0.1.
     */
    @FXML
    void initialize() {
        SpinnerValueFactory<Double> valueFactory = new SpinnerValueFactory.DoubleSpinnerValueFactory(0.0, 1000.0, 0.0, 0.1);
        foodPrice.setValueFactory(valueFactory);
    }

    /**
     * Removes a food item from the event's menu.
     * Prompts the user to enter the event ID and food name, then attempts to remove the food item.
     *
     * @param event the action event
     */
    @FXML
    void removeFood(ActionEvent event) {
        // Prompt user to enter the event ID
        TextInputDialog eventIdDialog = new TextInputDialog();
        eventIdDialog.setTitle("Remove Food");
        eventIdDialog.setHeaderText("Enter Event ID:");
        eventIdDialog.setContentText("Event ID:");

        String eventId = eventIdDialog.showAndWait().orElse("");

        if (eventId.isEmpty()) {
            showAlert(AlertType.ERROR, "Input Error", "Event ID must be provided.");
            return;
        }

        // Retrieve the event from the model
        EventModel model = EventModel.getInstance();
        EventList eventList = model.getEventList();
        Event existingEvent = eventList.getEventById(eventId);

        if (existingEvent == null) {
            showAlert(AlertType.ERROR, "Event Not Found", "No event found with the provided ID.");
            return;
        }

        // Prompt user to enter the food name
        TextInputDialog foodNameDialog = new TextInputDialog();
        foodNameDialog.setTitle("Remove Food");
        foodNameDialog.setHeaderText("Enter Food Name:");
        foodNameDialog.setContentText("Food Name:");

        String foodName = foodNameDialog.showAndWait().orElse("");

        if (foodName.isEmpty()) {
            showAlert(AlertType.ERROR, "Input Error", "Food name must be provided.");
            return;
        }

        // Attempt to remove the food item from the menu
        Food removed = existingEvent.removeFoodItem(foodName);

        if (removed != null) {
            showAlert(AlertType.INFORMATION, "Success", "Food item '" + removed.getName() + "' has been successfully removed from the menu.");
        } else {
            showAlert(AlertType.ERROR, "Removal Error", "Food item '" + foodName + "' not found in the menu.");
        }
    }

    /**
     * Saves the food item details and adds it to the event's menu.
     * Validates the inputs before adding the food item.
     *
     * @param event the action event
     */
    @FXML
    void saveAndExit(ActionEvent event) {
        String eventId = eventID.getText().trim();
        String food = foodName.getText().trim();
        Double price = foodPrice.getValue();

        if (eventId.isEmpty() || food.isEmpty() || price == null || price <= 0) {
            showAlert(AlertType.ERROR, "Input Error", "All fields must be filled and price must be greater than zero.");
            return;
        }

        EventModel model = EventModel.getInstance();
        EventList eventList = model.getEventList();
        Event existingEvent = eventList.getEventById(eventId);

        if (existingEvent == null) {
            showAlert(AlertType.ERROR, "Event Not Found", "No event found with the provided ID.");
            return;
        }

        // Create a new food item and add it to the event's menu
        Food newFood = new Food(food, price);
        existingEvent.addFoodItem(newFood);

        showAlert(AlertType.INFORMATION, "Success", newFood.toString() + " Food item has been successfully added to the menu.");
        clearFields();
    }

    /**
     * Views the menu for a specific event.
     * Prompts the user to enter the event ID, then displays the menu items if available.
     *
     * @param event the action event
     */
    @FXML
    void viewmenu(ActionEvent event) {
        // Prompt user to enter the event ID
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("View Menu");
        dialog.setHeaderText("Enter Event ID:");
        dialog.setContentText("Event ID:");

        String eventId = dialog.showAndWait().orElse("");

        if (eventId.isEmpty()) {
            showAlert(AlertType.ERROR, "Input Error", "Event ID must be provided.");
            return;
        }

        // Retrieve the event from the model
        EventModel model = EventModel.getInstance();
        EventList eventList = model.getEventList();
        Event existingEvent = eventList.getEventById(eventId);

        if (existingEvent == null) {
            showAlert(AlertType.ERROR, "Event Not Found", "No event found with the provided ID.");
            return;
        }

        // Retrieve and display the menu items
        List<Food> menu = existingEvent.getMenuItems();
        if (menu == null || menu.isEmpty()) {
            showAlert(AlertType.INFORMATION, "Menu Details", "No menu items found for the provided event ID.");
            return;
        }

        // Build and display the menu details string
        StringBuilder menuDetails = new StringBuilder("Menu Details:\n");
        for (Food food : menu) {
            menuDetails.append("Food Name: ").append(food.getName())
                    .append(", Price: $").append(food.getPrice()).append("\n");
        }

        showAlert(AlertType.INFORMATION, "Menu Details", menuDetails.toString());
    }

    /**
     * Shows an alert with the specified type, title, and message.
     *
     * @param type    the type of alert
     * @param title   the title of the alert
     * @param message the message to display in the alert
     */
    private void showAlert(AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        Window stage = saveandExit.getScene().getWindow();
        alert.initOwner(stage);
        alert.show();
    }

    /**
     * Clears the input fields on the page.
     */
    private void clearFields() {
        eventID.clear();
        foodName.clear();
        foodPrice.getValueFactory().setValue(0.0);
    }
}
