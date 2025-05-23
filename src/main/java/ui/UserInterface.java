package ui;

import builders.ChipBuilder;
import builders.DrinkBuilder;
import builders.SandwichBuilder;
import builders.SignatureSandwichBuilder;
import models.Order;
import persistence.ReceiptManager;

import java.io.IOException;

import static utils.ConsolePrinter.printHeader;
import static utils.ConsolePrinter.printLine;
import static utils.UserInputUtils.intPrompt;

/**
 * Manages the console-based user interface for the DELI-cious POS system.
 * Handles user interaction for placing orders, reviewing summaries, and saving receipts.
 */
public class UserInterface {

    private final Order currentOrder = new Order();

    /**
     * Starts the user interface by showing the home screen.
     */
    public void init() {
        homeScreen();
    }

    /**
     * Displays the home screen menu and navigates to the order screen or exits.
     */
    private void homeScreen() {
        while (true) {
            printHeader("Home Screen");
            printLine("[1] - New Order");
            printLine("[0] - Exit");

            int choice = intPrompt("Choose: ");
            switch (choice) {
                case 1 -> orderScreen();
                case 0 -> {
                    printLine("Goodbye!");
                    System.exit(0);
                }
                default -> printLine("Invalid option.");
            }
        }
    }

    /**
     * Displays the order screen where the user can build and manage an order.
     */
    private void orderScreen() {
        currentOrder.clear();
        while (true) {
            printHeader("Order Screen");
            printLine("[1] - Add Sandwich");
            printLine("[2] - Add Signature Sandwich");
            printLine("[3] - Add Drink");
            printLine("[4] - Add Chips");
            printLine("[5] - Checkout");
            printLine("[0] - Cancel Order");

            int choice = intPrompt("Choose: ");
            switch (choice) {
                case 1 -> currentOrder.addItem(new SandwichBuilder().build());
                case 2 -> {
                    var signature = new SignatureSandwichBuilder().build();
                    if (signature != null) currentOrder.addItem(signature);
                }
                case 3 -> currentOrder.addItem(new DrinkBuilder().build());
                case 4 -> currentOrder.addItem(new ChipBuilder().build());
                case 5 -> {
                    checkoutScreen();
                    return;
                }
                case 0 -> {
                    printLine("Order canceled.");
                    return;
                }
                default -> printLine("Invalid option.");
            }
        }
    }

    /**
     * Finalizes the order by displaying a summary and confirming whether to save it as a receipt.
     */
    private void checkoutScreen() {
        printHeader("Checkout");

        if (currentOrder.isEmpty()) {
            printLine("No items in the order.");
            return;
        }

        currentOrder.printSummary(System.out);
        double total = currentOrder.getPrice().doubleValue();

        System.out.printf("Total: $%.2f%n", total);
        printLine("[1] - Confirm Order");
        printLine("[0] - Cancel Order");

        int confirm = intPrompt("Choose: ");
        if (confirm == 1) {
            try {
                new ReceiptManager().saveOrderReceipt(currentOrder.getItems(), total);
                printLine("Order confirmed and saved. Thank you!");
            } catch (IOException e) {
                printLine("Failed to save receipt: " + e.getMessage());
            }
        } else {
            printLine("Order canceled.");
        }
    }
}
