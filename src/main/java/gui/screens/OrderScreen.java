package gui.screens;

import data.SignatureSandwiches;
import gui.util.StyledVBox;
import javafx.scene.control.*;
import javafx.stage.Stage;
import models.*;
import models.enums.*;
import persistence.ReceiptManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class OrderScreen extends StyledVBox {

    private final Order currentOrder = new Order();
    private final TextArea receiptArea = new TextArea();
    private final Button checkoutBtn = new Button("💵 Checkout");
    private final Stage stage;

    public OrderScreen(Stage stage) {
        this.stage = stage;

        Label title = new Label("🛒 Build Your Order");
        title.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        Button sandwichBtn = new Button("\uD83E\uDD56 Add Custom Sandwich");
        Button signatureBtn = new Button("\uD83D\uDC98 Add Signature Sandwich");
        Button drinkBtn = new Button("\uD83C\uDF79 Add Drink");
        Button chipBtn = new Button("\uD83C\uDF5F Add Chips");
        Button backBtn = new Button("\uD83D\uDD19 Back to Menu");

        styleButton(sandwichBtn, signatureBtn, drinkBtn, chipBtn, checkoutBtn, backBtn);

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

        checkoutBtn.setOnAction(e -> checkout());
        backBtn.setOnAction(e -> stage.getScene().setRoot(new MainMenu(stage)));

        receiptArea.setEditable(false);
        receiptArea.setPrefHeight(300);
        receiptArea.setWrapText(true);

        getChildren().addAll(title, sandwichBtn, signatureBtn, drinkBtn, chipBtn, receiptArea, checkoutBtn, backBtn);
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

    private Sandwich buildSignatureSandwichViaDialog() {
        List<SignatureSandwich> options = SignatureSandwiches.getAll();
        ChoiceDialog<SignatureSandwich> dialog = new ChoiceDialog<>(options.get(0), options);
        dialog.setTitle("Choose Signature Sandwich");
        dialog.setHeaderText("Select a preset sandwich to add to your order");
        dialog.setContentText("Sandwich:");
        Optional<SignatureSandwich> result = dialog.showAndWait();
        return result.map(base -> new SignatureSandwich(
                base.getName(),
                base.getSandwichSize(),
                base.getBreadType(),
                new ArrayList<>(base.getToppings()),
                new ArrayList<>(base.getExtraToppings()),
                base.getIsToasted()
        )).orElse(null);
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

        List<Topping> selectedToppings = new ArrayList<>();
        for (ToppingType type : ToppingType.values()) {
            if (confirm("Do you want to add " + type.name().toLowerCase().replace("_", " ") + " toppings?")) {
                selectedToppings.addAll(promptToppings("Choose " + type.name() + " Toppings", Topping.getByType(type)));
            }
        }

        List<Topping> extraToppings = new ArrayList<>();
        if (confirm("Add extra toppings?")) {
            for (ToppingType type : ToppingType.values()) {
                if (confirm("Extra " + type.name().toLowerCase().replace("_", " ") + " toppings?")) {
                    extraToppings.addAll(promptToppings("Choose Extra " + type.name() + " Toppings", Topping.getByType(type)));
                }
            }
        }

        Alert toastAlert = new Alert(Alert.AlertType.CONFIRMATION);
        toastAlert.setTitle("Toasted?");
        toastAlert.setHeaderText("Do you want your sandwich toasted?");
        toastAlert.setContentText("Click OK for Yes, Cancel for No.");
        boolean isToasted = toastAlert.showAndWait().filter(btn -> btn == ButtonType.OK).isPresent();

        return new Sandwich(sizeResult.get(), breadResult.get(), selectedToppings, extraToppings, isToasted);
    }

    private List<Topping> promptToppings(String title, List<Topping> options) {
        List<Topping> selected = new ArrayList<>();
        List<String> optionNames = options.stream().map(Enum::name).collect(Collectors.toList());
        ChoiceDialog<String> dialog = new ChoiceDialog<>(optionNames.get(0), optionNames);
        dialog.setTitle(title);
        dialog.setHeaderText(null);
        dialog.setContentText("Select one (reopen to add more):");

        while (true) {
            Optional<String> result = dialog.showAndWait();
            if (result.isEmpty()) break;
            selected.add(Topping.valueOf(result.get()));
            if (!confirm("Add another topping of this type?")) break;
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

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
