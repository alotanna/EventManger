package com.example.dsaproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Controller class for managing events in the application.
 * Handles user interactions and updates the content displayed in the `ManageEventsPage`.
 */
public class ManageEventsController {

    @FXML
    private Button Activitiesbutton;

    @FXML
    private Button BillsButton;

    @FXML
    private Button HomeButton;

    @FXML
    private Button LogoutButton;

    @FXML
    private AnchorPane MainContentPane;

    @FXML
    private Button Venuesbutton;

    @FXML
    private Button createvents_button;

    @FXML
    private Button menuButton;

    @FXML
    private ScrollBar scrollbar;

    @FXML
    private ImageView sunsetimage;

    @FXML
    private TextFlow viewSummariesfield;

    @FXML
    private Button vieweventsbutton;

    /**
     * Loads the "CreateEvents.fxml" page when the create events button is clicked.
     *
     * @param event the action event triggered by clicking the button
     */
    @FXML
    void createEvents(ActionEvent event) {
        loadPage("CreateEvents.fxml");
    }

    /**
     * Returns to the home page when the home button is clicked.
     *
     * @param event the action event triggered by clicking the button
     */
    @FXML
    void gohome(ActionEvent event) {
        try {
            Stage stage = (Stage) HomeButton.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("ManageEventsPage.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Logs out the user and returns to the login page when the logout button is clicked.
     *
     * @param event the action event triggered by clicking the button
     */
    @FXML
    void logOut(ActionEvent event) {
        try {
            Stage stage = (Stage) LogoutButton.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads the "ActivityManger.fxml" page when the view activities button is clicked.
     *
     * @param event the action event triggered by clicking the button
     */
    @FXML
    void viewActivities(ActionEvent event) {
        loadPage("ActivityManger.fxml");
    }

    /**
     * Loads the "EventsPage.fxml" page when the view events button is clicked.
     *
     * @param event the action event triggered by clicking the button
     */
    @FXML
    void viewEvents(ActionEvent event) {
        loadPage("EventsPage.fxml");
    }

    /**
     * Loads the "ModifyVenue.fxml" page when the modify venues button is clicked.
     *
     * @param event the action event triggered by clicking the button
     */
    @FXML
    void modifyvenues(ActionEvent event) {
        loadPage("ModifyVenue.fxml");
    }

    /**
     * Loads the "MenuPage.fxml" page when the view foods on menu button is clicked.
     *
     * @param event the action event triggered by clicking the button
     */
    @FXML
    void viewFoodsOnMenu(ActionEvent event) {
        loadPage("MenuPage.fxml");
    }

    /**
     * Loads the "BillsPage.fxml" page when the view bills button is clicked.
     *
     * @param event the action event triggered by clicking the button
     */
    @FXML
    void viewBills(ActionEvent event) {
        loadPage("BillsPage.fxml");
    }

    /**
     * Loads a new page specified by the FXML file name into the main content pane.
     *
     * @param fxmlFileName the name of the FXML file to load
     */
    private void loadPage(String fxmlFileName) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFileName));
            AnchorPane newPane = loader.load();
            MainContentPane.getChildren().setAll(newPane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Updates the summary view with a list of events.
     * If no events are available, displays a message prompting the user to book events.
     */
    @FXML
    void updateSummaryView() {
        EventModel model = EventModel.getInstance();
        EventList eventList = model.getEventList();

        viewSummariesfield.getChildren().clear();

        if (eventList.getSize() == 0) {
            viewSummariesfield.getChildren().add(new Text("No events at the moment. Let's get booking!"));
        } else {
            StringBuilder summary = new StringBuilder();
            for (Event event : eventList.getSortedEventsByDateAndTime()) {
                summary.append(event.toString()).append("\n\n");
            }
            viewSummariesfield.getChildren().add(new Text(summary.toString()));
        }
    }

    /**
     * Initializes the controller by updating the summary view when the page loads.
     */
    @FXML
    private void initialize() {
        updateSummaryView(); // Initialize the summary view when the page loads
    }
}
