package gui.screens;

import data.SignatureSandwiches;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import models.SignatureSandwich;
import persistence.ReceiptManager;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

public class MainMenu extends VBox {

    private final Stage stage;

    public MainMenu(Stage stage) {
        this.stage = stage;
        setSpacing(40);
        setPadding(new Insets(80));
        setAlignment(Pos.CENTER);

        Label title = new Label("Welcome to the Sandwich Shop POS");
        title.setStyle("-fx-font-size: 36px; -fx-font-weight: bold; -fx-text-fill: #333;");

        Button newOrderBtn = createButton("Start New Order", "/images/order.png");
        Button viewReceiptBtn = createButton("View Receipt by ID", "/images/receipt.png");
        Button exitBtn = createButton("Exit", "/images/exit.png");

        newOrderBtn.setOnAction(e -> showOrderScreen());
        viewReceiptBtn.setOnAction(e -> showReceiptPrompt());
        exitBtn.setOnAction(e -> stage.close());

        getChildren().addAll(title, newOrderBtn, viewReceiptBtn, exitBtn);
    }

    private Button createButton(String text, String iconPath) {
        Button button = new Button(text);
        button.setPrefWidth(300);
        button.setPrefHeight(60);
        button.setStyle("-fx-font-size: 18px; -fx-background-color: #ffe5b4; -fx-text-fill: #3a3a3a; -fx-background-radius: 10;");
        try {
            ImageView icon = new ImageView(new Image(getClass().getResourceAsStream(iconPath)));
            icon.setFitWidth(24);
            icon.setFitHeight(24);
            button.setGraphic(icon);
        } catch (Exception e) {
            System.out.println("Icon missing: " + iconPath);
        }
        return button;
    }

    private void showOrderScreen() {
        OrderScreen orderScreen = new OrderScreen(stage);
        Scene scene = new Scene(orderScreen, 1920, 1080);
        scene.getStylesheets().add(getClass().getResource("/styles/style.css").toExternalForm());
        stage.setScene(scene);
    }

    private void showReceiptPrompt() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("View Receipt");
        dialog.setHeaderText("Enter Receipt ID");
        dialog.setContentText("Receipt ID (e.g., 20250523-172201):");

        Optional<String> result = dialog.showAndWait();
        result.ifPresent(id -> {
            String path = "src/main/resources/receipt/" + id;
            File file = new File(path);
            if (!file.exists()) {
                showError("Receipt Not Found", "No receipt found with that ID.");
                return;
            }

            try {
                String content = Files.readString(file.toPath());
                showReceiptOnScreen(id, content);
            } catch (IOException ex) {
                showError("Read Error", "Could not read receipt: " + ex.getMessage());
            }
        });
    }

    private void showReceiptOnScreen(String id, String content) {
        TextArea receiptView = new TextArea();
        receiptView.setText("=== Receipt " + id + " ===\n\n" + content);
        receiptView.setEditable(false);
        receiptView.setWrapText(true);
        receiptView.setPrefSize(1400, 800);
        receiptView.setStyle("-fx-font-family: 'monospace'; -fx-font-size: 14px;");

        Button backBtn = new Button("Back");
        backBtn.setPrefWidth(200);

        VBox layout = new VBox(30, new Label("📄 Receipt Details"), receiptView, backBtn);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(40));

        backBtn.setOnAction(e -> {
            MainMenu mainMenu = new MainMenu(stage);
            Scene scene = new Scene(mainMenu, 1920, 1080);
            scene.getStylesheets().add(getClass().getResource("/styles/style.css").toExternalForm());
            stage.setScene(scene);
        });

        Scene scene = new Scene(layout, 1920, 1080);
        scene.getStylesheets().add(getClass().getResource("/styles/style.css").toExternalForm());
        stage.setScene(scene);
    }

    private void showError(String title, String msg) {
        Alert error = new Alert(Alert.AlertType.ERROR);
        error.setTitle("Error");
        error.setHeaderText(title);
        error.setContentText(msg);
        error.showAndWait();
    }

    public static String formatEnum(Enum<?> e) {
        return Arrays.stream(e.name().toLowerCase().split("_"))
                .map(word -> word.substring(0, 1).toUpperCase() + word.substring(1))
                .collect(Collectors.joining(" "));
    }

    public static String formatSignature(SignatureSandwich s) {
        return s.getName();
    }
}