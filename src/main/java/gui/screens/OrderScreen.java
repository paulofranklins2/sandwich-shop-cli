package gui.screens;

import data.SignatureSandwiches;
import gui.helpers.DialogBuilder;
import gui.helpers.DialogUtils;
import gui.util.StyledVBox;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import models.Order;
import persistence.ReceiptManager;

import java.io.IOException;

/**
 * Screen to build a customer order.
 * Handles layout and delegates dialog logic to helpers.
 */
public class OrderScreen extends StyledVBox {

    private final Order currentOrder = new Order();
    private final TextArea receiptArea = new TextArea();

    public OrderScreen(Stage stage) {

        Label title = new Label("🛒 Build Your Order");
        title.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        Button sandwichBtn = new Button("\uD83E\uDD56 Add Custom Sandwich");
        Button signatureBtn = new Button("\uD83D\uDC98 Add Signature Sandwich");
        Button drinkBtn = new Button("\uD83C\uDF79 Add Drink");
        Button chipBtn = new Button("\uD83C\uDF5F Add Chips");
        Button backBtn = new Button("\uD83D\uDD19 Back to Menu");
        Button checkoutBtn = new Button("\uD83D\uDCB5 Checkout");

        styleButton(sandwichBtn, signatureBtn, drinkBtn, chipBtn, checkoutBtn, backBtn);

        sandwichBtn.setOnAction(e -> addItem(DialogBuilder.buildCustomSandwich()));
        signatureBtn.setOnAction(e -> addItem(DialogBuilder.buildSignatureSandwich(SignatureSandwiches.getAll())));
        drinkBtn.setOnAction(e -> addItem(DialogBuilder.buildDrink()));
        chipBtn.setOnAction(e -> addItem(DialogBuilder.buildChip()));
        checkoutBtn.setOnAction(e -> checkout());
        backBtn.setOnAction(e -> stage.getScene().setRoot(new MainMenu(stage)));

        receiptArea.setEditable(false);
        receiptArea.setPrefHeight(300);
        receiptArea.setWrapText(true);

        getChildren().addAll(title, sandwichBtn, signatureBtn, drinkBtn, chipBtn, receiptArea, checkoutBtn, backBtn);
    }

    private void addItem(interfaces.MenuItem item) {
        if (item != null) {
            currentOrder.addItem(item);
            updateReceipt();
        }
    }

    private void updateReceipt() {
        receiptArea.clear();
        var out = new java.io.ByteArrayOutputStream();
        currentOrder.printSummary(new java.io.PrintStream(out));
        receiptArea.setText(out.toString());
    }

    private void checkout() {
        if (currentOrder.isEmpty()) {
            DialogUtils.showAlert(Alert.AlertType.WARNING, "Empty Order", "Please add items before checking out.");
            return;
        }

        double total = currentOrder.getPrice().doubleValue();
        try {
            String receiptNumber = new ReceiptManager().saveOrderReceipt(currentOrder.getItems(), total);
            DialogUtils.showAlert(Alert.AlertType.INFORMATION, "Order Saved!", "Your receipt ID is: " + receiptNumber);
            currentOrder.clear();
            receiptArea.clear();
        } catch (IOException e) {
            DialogUtils.showAlert(Alert.AlertType.ERROR, "Save Failed", "Could not save receipt: " + e.getMessage());
        }
    }
}
