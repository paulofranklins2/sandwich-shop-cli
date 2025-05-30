package gui;

import gui.screens.MainMenu;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainFX extends Application {
    @Override
    public void start(Stage primaryStage) {
        MainMenu root = new MainMenu(primaryStage);
        Scene scene = new Scene(root, 1920, 1080);
        scene.getStylesheets().add(getClass().getResource("/styles/style.css").toExternalForm());

        primaryStage.setTitle("🥪 Sandwich Shop POS");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);

        primaryStage.setAlwaysOnTop(true);     // Force to front
        primaryStage.show();
        primaryStage.toFront();
        primaryStage.requestFocus();
        primaryStage.setAlwaysOnTop(false);    // Reset after showing
    }

}
