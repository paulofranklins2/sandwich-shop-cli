package gui;

import gui.screens.MainMenu;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class MainFX extends Application {
    @Override
    public void start(Stage primaryStage) {
        MainMenu root = new MainMenu(primaryStage);
        Scene scene = new Scene(root, 700, 450);
        scene.getStylesheets().add(getClass().getResource("/styles/style.css").toExternalForm());

        primaryStage.setTitle("🥪 Sandwich Shop POS");
//        primaryStage.getIcons().add(new Image("logo.png")); // optional
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
