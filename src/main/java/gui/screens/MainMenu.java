package gui.screens;

import gui.util.StyledVBox;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Objects;
import java.util.Optional;

/**
 * The main screen users see when the app starts. Allows starting an order or viewing receipts.
 */
public class MainMenu extends StyledVBox {
    private final Stage stage;

    public MainMenu(Stage stage) {
        this.stage = stage;

        Label title = new Label("\uD83C\uDFEA Welcome to the Sandwich Shop POS");
        title.setStyle("-fx-font-size: 36px; -fx-font-weight: bold; -fx-text-fill: #333;");

        Button newOrderBtn = new Button("\uD83D\uDCDD Start New Order");
        Button viewReceiptBtn = new Button("\uD83D\uDCC4 View Receipt by ID");
        Button exitBtn = new Button("\uD83D\uDD12 Exit");

//        addIcon(newOrderBtn, "/images/order.png");
//        addIcon(viewReceiptBtn, "/images/receipt.png");
//        addIcon(exitBtn, "/images/exit.png");

        styleButton(newOrderBtn, viewReceiptBtn, exitBtn);

        newOrderBtn.setOnAction(e -> showOrderScreen());
        viewReceiptBtn.setOnAction(e -> showReceiptPrompt());
        exitBtn.setOnAction(e -> stage.close());

        getChildren().addAll(title, newOrderBtn, viewReceiptBtn, exitBtn);
    }

    private void showOrderScreen() {
        OrderScreen orderScreen = new OrderScreen(stage);
        Scene scene = new Scene(orderScreen, 1920, 1080);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/styles/style.css")).toExternalForm());
        stage.setScene(scene);
    }

    private void showReceiptPrompt() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("View Receipt");
        dialog.setHeaderText("Enter Receipt ID");
        dialog.setContentText("Receipt ID (e.g., 20250523-172201):");

        Optional<String> result = dialog.showAndWait();
        result.ifPresent(this::loadAndDisplayReceipt);
    }

    private void loadAndDisplayReceipt(String id) {
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
    }

    private void showReceiptOnScreen(String id, String content) {
        TextArea receiptView = new TextArea("=== Receipt " + id + " ===\n\n" + content);
        receiptView.setEditable(false);
        receiptView.setWrapText(true);
        receiptView.setPrefSize(1400, 800);
        receiptView.setStyle("-fx-font-family: 'monospace'; -fx-font-size: 14px;");

        Button backBtn = new Button("Back");
        styleButton(backBtn);

        StyledVBox layout = new StyledVBox();
        layout.setSpacing(20);
        layout.getChildren().addAll(new Label("\uD83D\uDCC4 Receipt Details"), receiptView, backBtn);

        backBtn.setOnAction(e -> {
            Scene scene = new Scene(new MainMenu(stage), 1920, 1080);
            scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/styles/style.css")).toExternalForm());
            stage.setScene(scene);
        });

        Scene scene = new Scene(layout, 1920, 1080);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/styles/style.css")).toExternalForm());
        stage.setScene(scene);
    }

    private void showError(String title, String msg) {
        Alert error = new Alert(Alert.AlertType.ERROR);
        error.setTitle("Error");
        error.setHeaderText(title);
        error.setContentText(msg);
        error.showAndWait();
    }

    private void addIcon(Button button, String iconPath) {
        try {
            ImageView icon = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream(iconPath))));
            icon.setFitWidth(24);
            icon.setFitHeight(24);
            button.setGraphic(icon);
        } catch (Exception e) {
            System.out.println("Missing icon: " + iconPath);
        }
    }
}