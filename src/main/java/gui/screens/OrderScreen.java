package gui.screens;

import data.SignatureSandwiches;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import models.Order;
import models.Sandwich;
import models.SignatureSandwich;
import models.Chip;
import models.Drink;
import models.enums.*;
import persistence.ReceiptManager;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;
import java.util.stream.Collectors;

public class OrderScreen extends VBox {

    private final Order currentOrder = new Order();
    private final TextArea receiptArea = new TextArea();
    private final Button checkoutBtn = new Button("💵 Checkout");
    private final Stage stage;

    public OrderScreen(Stage stage) {
        this.stage = stage;

        setSpacing(20);
        setPadding(new Insets(30));
        setAlignment(Pos.CENTER);

        Label title = new Label("🛒 Build Your Order");
        title.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        Button sandwichBtn = new Button("🥖 Add Custom Sandwich");
        Button signatureBtn = new Button("⭐ Add Signature Sandwich");
        Button drinkBtn = new Button("🥤 Add Drink");
        Button chipBtn = new Button("🍟 Add Chips");
        Button viewReceiptBtn = new Button("📄 View Receipt by ID");
        Button backBtn = new Button("🔙 Back to Menu");

        style(sandwichBtn, signatureBtn, drinkBtn, chipBtn, viewReceiptBtn, checkoutBtn, backBtn);

        sandwichBtn.setOnAction(e -> {
            Sandwich s = buildCustomSandwichViaDialog();
            if (s != null) currentOrder.addItem(s);
            updateReceipt();
        });

        signatureBtn.setOnAction(e -> {
            Sandwich s = buildSignatureSandwichViaDialog();
            if (s != null) currentOrder.addItem(s);
            updateReceipt();
        });

        drinkBtn.setOnAction(e -> {
            Drink drink = buildDrinkViaDialog();
            if (drink != null) currentOrder.addItem(drink);
            updateReceipt();
        });

        chipBtn.setOnAction(e -> {
            Chip chip = buildChipViaDialog();
            if (chip != null) currentOrder.addItem(chip);
            updateReceipt();
        });

        viewReceiptBtn.setOnAction(e -> showReceiptById());
        checkoutBtn.setOnAction(e -> checkout());
        backBtn.setOnAction(e -> stage.getScene().setRoot(new MainMenu(stage)));

        receiptArea.setEditable(false);
        receiptArea.setPrefHeight(200);
        receiptArea.setWrapText(true);

        getChildren().addAll(title, sandwichBtn, signatureBtn, drinkBtn, chipBtn, viewReceiptBtn, receiptArea, checkoutBtn, backBtn);
    }

    private void style(Button... buttons) {
        for (Button b : buttons) {
            b.setPrefWidth(250);
            b.setPrefHeight(40);
        }
    }

    private void updateReceipt() {
        receiptArea.clear();
        var out = new java.io.ByteArrayOutputStream();
        var print = new java.io.PrintStream(out);
        currentOrder.printSummary(print);
        receiptArea.setText(out.toString());
    }

    private void checkout() {
        if (currentOrder.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Empty Order", "Please add items before checking out.");
            return;
        }

        double total = currentOrder.getPrice().doubleValue();
        try {
            String receiptNumber = new ReceiptManager().saveOrderReceipt(currentOrder.getItems(), total);
            showAlert(Alert.AlertType.INFORMATION, "Order Saved!", "Your receipt ID is: " + receiptNumber);
            currentOrder.clear();
            receiptArea.clear();
        } catch (IOException e) {
            showAlert(Alert.AlertType.ERROR, "Save Failed", "Could not save receipt: " + e.getMessage());
        }
    }

    private void showReceiptById() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("View Receipt");
        dialog.setHeaderText("Enter Receipt ID");
        dialog.setContentText("Receipt ID (e.g., 20250523-172201):");

        Optional<String> result = dialog.showAndWait();
        result.ifPresent(id -> {
            String path = "src/main/resources/receipt/" + id;
            File file = new File(path);
            if (!file.exists()) {
                showAlert(Alert.AlertType.ERROR, "Not Found", "No receipt found with that ID.");
                return;
            }
            try {
                String content = Files.readString(file.toPath());
                receiptArea.setText("=== Receipt " + id + " ===\n" + content);
            } catch (IOException e) {
                showAlert(Alert.AlertType.ERROR, "Read Error", "Could not read receipt: " + e.getMessage());
            }
        });
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private Sandwich buildSignatureSandwichViaDialog() {
        List<SignatureSandwich> options = SignatureSandwiches.getAll();

        ChoiceDialog<SignatureSandwich> dialog = new ChoiceDialog<>(options.get(0), options);
        dialog.setTitle("Choose Signature Sandwich");
        dialog.setHeaderText("Select a preset sandwich to add to your order");
        dialog.setContentText("Sandwich:");

        Optional<SignatureSandwich> result = dialog.showAndWait();
        return result.map(base ->
                new SignatureSandwich(
                        base.getName(),
                        base.getSandwichSize(),
                        base.getBreadType(),
                        new ArrayList<>(base.getToppings()),
                        new ArrayList<>(base.getExtraToppings()),
                        base.getIsToasted()
                )
        ).orElse(null);
    }

    private Chip buildChipViaDialog() {
        ChoiceDialog<ChipFlavor> dialog = new ChoiceDialog<>(ChipFlavor.values()[0], ChipFlavor.values());
        dialog.setTitle("Choose Chips");
        dialog.setHeaderText("Select a chip flavor");
        dialog.setContentText("Flavor:");

        Optional<ChipFlavor> result = dialog.showAndWait();
        return result.map(Chip::new).orElse(null);
    }

    private Drink buildDrinkViaDialog() {
        ChoiceDialog<DrinkSize> sizeDialog = new ChoiceDialog<>(DrinkSize.MEDIUM, DrinkSize.values());
        sizeDialog.setTitle("Drink Size");
        sizeDialog.setHeaderText("Select drink size");
        sizeDialog.setContentText("Size:");

        Optional<DrinkSize> sizeResult = sizeDialog.showAndWait();
        if (sizeResult.isEmpty()) return null;

        ChoiceDialog<DrinkFlavor> flavorDialog = new ChoiceDialog<>(DrinkFlavor.values()[0], DrinkFlavor.values());
        flavorDialog.setTitle("Drink Flavor");
        flavorDialog.setHeaderText("Select drink flavor");
        flavorDialog.setContentText("Flavor:");

        Optional<DrinkFlavor> flavorResult = flavorDialog.showAndWait();
        return flavorResult.map(flavor -> new Drink(sizeResult.get(), flavor)).orElse(null);
    }

    private Sandwich buildCustomSandwichViaDialog() {
        ChoiceDialog<BreadType> breadDialog = new ChoiceDialog<>(BreadType.WHITE, BreadType.values());
        breadDialog.setTitle("Bread Type");
        breadDialog.setHeaderText("Choose your bread");
        breadDialog.setContentText("Bread:");

        Optional<BreadType> breadResult = breadDialog.showAndWait();
        if (breadResult.isEmpty()) return null;

        ChoiceDialog<SandwichSize> sizeDialog = new ChoiceDialog<>(SandwichSize.EIGHT_INCH, SandwichSize.values());
        sizeDialog.setTitle("Sandwich Size");
        sizeDialog.setHeaderText("Choose your sandwich size");
        sizeDialog.setContentText("Size:");

        Optional<SandwichSize> sizeResult = sizeDialog.showAndWait();
        if (sizeResult.isEmpty()) return null;

        List<Topping> selectedToppings = promptToppings("Choose Toppings", Topping.values());
        List<Topping> extraToppings = promptToppings("Add Extra Toppings?", Topping.values());

        Alert toastAlert = new Alert(Alert.AlertType.CONFIRMATION);
        toastAlert.setTitle("Toasted?");
        toastAlert.setHeaderText("Do you want your sandwich toasted?");
        toastAlert.setContentText("Click OK for Yes, Cancel for No.");

        boolean isToasted = toastAlert.showAndWait().filter(btn -> btn == ButtonType.OK).isPresent();

        return new Sandwich(sizeResult.get(), breadResult.get(), selectedToppings, extraToppings, isToasted);
    }

    private List<Topping> promptToppings(String title, Topping[] options) {
        List<Topping> selected = new ArrayList<>();
        List<String> optionNames = Arrays.stream(options).map(Enum::name).collect(Collectors.toList());

        ChoiceDialog<String> dialog = new ChoiceDialog<>(optionNames.get(0), optionNames);
        dialog.setTitle(title);
        dialog.setHeaderText(null);
        dialog.setContentText("Select one (reopen dialog to add more):");

        boolean adding = true;
        while (adding) {
            Optional<String> result = dialog.showAndWait();
            result.ifPresent(name -> selected.add(Topping.valueOf(name)));
            adding = confirm("Add another?");
        }
        return selected;
    }

    private boolean confirm(String message) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText(message);
        Optional<ButtonType> result = alert.showAndWait();
        return result.filter(btn -> btn == ButtonType.OK).isPresent();
    }
}
