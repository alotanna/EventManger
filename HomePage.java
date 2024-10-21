package com.example.dsaproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Controller class for the home page login functionality.
 */
public class HomePage {

    @FXML
    private PasswordField UserPassword;

    @FXML
    private Button loginButton;

    @FXML
    private TextField userName;

    // Hardcoded credentials for authentication
    private static final String CORRECT_USERNAME = "abc";
    private static final String CORRECT_PASSWORD = "123";

    /**
     * Handles the login button click event.
     * Checks the provided username and password against the hardcoded credentials.
     * If the credentials match, loads the ManageEventsPage.fxml and sets it as the current scene.
     * If the credentials do not match, displays an error alert.
     *
     * @param event the ActionEvent triggered by clicking the login button
     */
    @FXML
    void handleLoginButton(ActionEvent event) {
        String username = userName.getText();
        String password = UserPassword.getText();

        if (CORRECT_USERNAME.equals(username) && CORRECT_PASSWORD.equals(password)) {
            // Load the ManageEventsPage.fxml page
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("ManageEventsPage.fxml"));
                Parent root = loader.load();

                // Get the current stage and set new scene
                Stage stage = (Stage) loginButton.getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
                // Handle exception
            }
        } else {
            // Show error alert
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Login Error");
            alert.setHeaderText(null);
            alert.setContentText("Incorrect username or password. Please try again.");
            alert.showAndWait();
        }
    }
}
