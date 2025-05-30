// OrderScreen.java — handles GUI for order building
package gui.screens;

import data.SignatureSandwiches;
import gui.util.StyledVBox;
import javafx.scene.control.*;
import javafx.stage.Stage;
import models.*;
import models.enums.*;
import persistence.ReceiptManager;
import utils.ConsolePrinter;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * This screen lets the user build an order:
 * add custom sandwiches, signature ones, drinks, chips, and check out.
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

        sandwichBtn.setOnAction(e -> addItem(buildCustomSandwichViaDialog()));
        signatureBtn.setOnAction(e -> addItem(buildSignatureSandwichViaDialog()));
        drinkBtn.setOnAction(e -> addItem(buildDrinkViaDialog()));
        chipBtn.setOnAction(e -> addItem(buildChipViaDialog()));
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

    private interfaces.MenuItem buildSignatureSandwichViaDialog() {
        List<SignatureSandwich> options = SignatureSandwiches.getAll();
        ChoiceDialog<SignatureSandwich> dialog = new ChoiceDialog<>(options.getFirst(), options);
        dialog.setTitle("Choose Signature Sandwich");
        dialog.setHeaderText("Pick a pre-made sandwich");
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

    private interfaces.MenuItem buildChipViaDialog() {
        List<String> chipOptions = Arrays.stream(ChipFlavor.values())
                .map(f -> ConsolePrinter.capitalizeWords(f.name().toLowerCase().replace("_", " ")))
                .collect(Collectors.toList());

        ChoiceDialog<String> dialog = new ChoiceDialog<>(chipOptions.getFirst(), chipOptions);
        dialog.setTitle("Choose Chips");
        dialog.setHeaderText("Pick a chip flavor");
        dialog.setContentText("Flavor:");
        Optional<String> result = dialog.showAndWait();

        return result.flatMap(flavorStr ->
                Arrays.stream(ChipFlavor.values())
                        .filter(f -> ConsolePrinter.capitalizeWords(f.name().toLowerCase().replace("_", " ")).equals(flavorStr))
                        .findFirst()
                        .map(Chip::new)
        ).orElse(null);
    }

    private interfaces.MenuItem buildDrinkViaDialog() {
        DrinkSize size = promptEnumOption("Drink Size", DrinkSize.values());
        if (size == null) return null;

        DrinkFlavor flavor = promptEnumOption("Drink Flavor", DrinkFlavor.values());
        if (flavor == null) return null;

        return new Drink(size, flavor);
    }

    private interfaces.MenuItem buildCustomSandwichViaDialog() {
        BreadType bread = promptEnumOption("Bread Type", BreadType.values());
        SandwichSize size = promptEnumOption("Sandwich Size", SandwichSize.values());
        if (bread == null || size == null) return null;

        List<Topping> toppings = chooseToppings(false);
        List<Topping> extraToppings = confirm("Add extra toppings?") ? chooseToppings(true) : new ArrayList<>();
        boolean isToasted = confirm("Do you want your sandwich toasted?");

        return new Sandwich(size, bread, toppings, extraToppings, isToasted);
    }

    private <T extends Enum<T>> T promptEnumOption(String title, T[] values) {
        List<String> options = Arrays.stream(values)
                .map(v -> ConsolePrinter.capitalizeWords(v.name().toLowerCase().replace("_", " ")))
                .collect(Collectors.toList());

        ChoiceDialog<String> dialog = new ChoiceDialog<>(options.getFirst(), options);
        dialog.setTitle(title);
        dialog.setHeaderText("Pick one");
        dialog.setContentText("Choice:");
        Optional<String> result = dialog.showAndWait();

        return result.flatMap(choice ->
                Arrays.stream(values)
                        .filter(v -> ConsolePrinter.capitalizeWords(v.name().toLowerCase().replace("_", " ")).equals(choice))
                        .findFirst()
        ).orElse(null);
    }

    private List<Topping> chooseToppings(boolean isExtra) {
        List<Topping> selected = new ArrayList<>();

        for (ToppingType type : ToppingType.values()) {
            if (confirm("Add " + (isExtra ? "extra " : "") + type.name().toLowerCase() + " toppings?")) {
                selected.addAll(promptToppings("Choose " + (isExtra ? "Extra " : "") + type.name() + " Toppings", Topping.getByType(type)));
            }
        }

        return selected;
    }

    private List<Topping> promptToppings(String title, List<Topping> options) {
        List<Topping> selected = new ArrayList<>();
        List<String> names = options.stream().map(Enum::name).collect(Collectors.toList());

        ChoiceDialog<String> dialog = new ChoiceDialog<>(names.getFirst(), names);
        dialog.setTitle(title);
        dialog.setHeaderText(null);
        dialog.setContentText("Pick a topping:");

        while (true) {
            Optional<String> result = dialog.showAndWait();
            if (result.isEmpty()) break;
            selected.add(Topping.valueOf(result.get()));
            if (!confirm("Add another topping?")) break;
        }

        return selected;
    }

    private boolean confirm(String message) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm");
        alert.setHeaderText(null);
        alert.setContentText(message);
        return alert.showAndWait().filter(btn -> btn == ButtonType.OK).isPresent();
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}