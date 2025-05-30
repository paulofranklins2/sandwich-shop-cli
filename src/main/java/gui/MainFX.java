package gui;

import gui.screens.MainMenu;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

/**
 * Launches the JavaFX Sandwich Shop POS GUI.
 */
public class MainFX extends Application {
    @Override
    public void start(Stage primaryStage) {
        // Create the main menu screen and apply CSS styling
        MainMenu mainMenu = new MainMenu(primaryStage);
        Scene scene = new Scene(mainMenu, 1920, 1080);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/styles/style.css")).toExternalForm());

        // Configure the stage (window)
        primaryStage.setTitle("\uD83E\uDD6A Sandwich Shop POS");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);

        // Bring the window to the front
        primaryStage.setAlwaysOnTop(true);
        primaryStage.show();
        primaryStage.toFront();
        primaryStage.requestFocus();
        primaryStage.setAlwaysOnTop(false);
    }
}
