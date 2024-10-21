package com.example.dsaproject;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * The main application class that extends {@link Application}.
 * This class sets up and launches the primary stage for the JavaFX application.
 */
public class MainApp extends Application {

    /**
     * The entry point for the JavaFX application.
     * Loads the FXML file for the login page, sets up the primary stage, and shows it.
     *
     * @param primaryStage the primary stage for this application
     * @throws Exception if an error occurs during loading the FXML file or setting up the stage
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        // Load the FXML file for the login page
        Parent root = FXMLLoader.load(getClass().getResource("HomePage.fxml"));

        // Set up the stage
        primaryStage.setTitle("Login Page");
        primaryStage.setScene(new Scene(root, 600, 400));

        // Make the stage non-resizable
        primaryStage.setResizable(false);

        // Show the stage
        primaryStage.show();
    }

    /**
     * The main method that launches the JavaFX application
     * @param args command-line arguments passed to the application
     */
    public static void main(String[] args) {
        launch(args);
    }
}
