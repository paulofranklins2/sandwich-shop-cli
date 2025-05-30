package ui;

import builders.ChipBuilder;
import builders.DrinkBuilder;
import builders.SandwichBuilder;
import builders.SignatureSandwichBuilder;
import gui.MainFX;
import javafx.application.Application;
import models.Order;
import persistence.ReceiptManager;

import java.io.IOException;

import static utils.ConsolePrinter.*;
import static utils.UserPromptUtils.*;

/**
 * Handles the command-line interface for the DELI-cious POS system.
 * Lets users place orders, check receipts, and manage their choices.
 */
public class UserInterface {

    private final Order currentOrder = new Order();
    private boolean running = true;

    /**
     * Starts the application by showing the main menu.
     */
    public void init() {
        mainMenu();
    }

    /**
     * Shows the main menu and handles user choices.
     */
    private void mainMenu() {
        while (running) {
            clearScreen();
            printMainMenu();

            int option = intPrompt("Choose: ");
            switch (option) {
                case 1 -> orderScreen();
                case 2 -> viewReceiptById();
                case 3 -> Application.launch(MainFX.class);
                case 0 -> {
                    System.out.println("Goodbye!");
                    running = false;
                }
                default -> System.out.println("Invalid option.");
            }
        }
    }

    /**
     * Lets the user look up a receipt by ID.
     */
    public void viewReceiptById() {
        scanner.nextLine(); // Clear extra character
        clearScreen();
        printHeader("Retrieve Receipt");
        String id = stringPrompt("Enter your receipt number (e.g., 20250523-172201): ");
        try {
            new ReceiptManager().loadReceiptById(id);
        } catch (IOException e) {
            System.out.println("Receipt not found: " + e.getMessage());
        } finally {
            promptToContinue();
        }
    }

    /**
     * Lets the user build an order step-by-step.
     */
    private void orderScreen() {
        currentOrder.clear();

        while (true) {
            clearScreen();
            printOrderMenu();

            int choice = intPrompt("Choose: ");
            switch (choice) {
                case 1 -> handleItemAddition( () ->
                        currentOrder
                                .addItem(new SandwichBuilder()
                                        .build()));
                case 2 -> handleItemAddition(() ->
                        currentOrder
                                .addItem(new SignatureSandwichBuilder()
                                        .build()));
                case 3 -> handleItemAddition(() ->
                        currentOrder
                                .addItem(new DrinkBuilder()
                                        .build()));
                case 4 -> handleItemAddition(() ->
                        currentOrder
                                .addItem(new ChipBuilder()
                                        .build()));
                case 5 -> {
                    handleItemAddition(this::checkoutScreen);
                    return;
                }
                case 0 -> {
                    handleActionWithPause(() ->
                            System.out.println("Order canceled."));
                    return;
                }
                default -> System.out.println("Invalid option.");
            }
        }
    }

    /**
     * Shows the order summary and lets the user confirm and save it.
     */
    private void checkoutScreen() {
        printHeader("Checkout");

        if (currentOrder.isEmpty()) {
            System.out.println("No items in the order.");
            return;
        }

        currentOrder.printSummary(System.out);
        double total = currentOrder.getPrice().doubleValue();

        System.out.printf("Total: $%.2f%n", total);
        System.out.println("[1] - Confirm Order");
        System.out.println("[0] - Cancel Order");

        int confirm = intPrompt("Choose: ");

        if (confirm == 1) {
            handleOrderConfirmation(total);
        } else {
            System.out.println("Order canceled.");
        }
    }

    /**
     * Handles the logic for saving the order receipt.
     */
    private void handleOrderConfirmation(double total) {
        try {
            String receiptNumber = new ReceiptManager().saveOrderReceipt(currentOrder.getItems(), total);
            System.out.println("Order confirmed and saved.");
            System.out.println("📄 Your receipt number: " + receiptNumber);
        } catch (IOException e) {
            System.out.println("Failed to save receipt: " + e.getMessage());
        }
    }

    /**
     * Handles the standard flow for adding an item.
     */
    private void handleItemAddition(Runnable builderAction) {
        clearScreen();
        builderAction.run();
        scanner.nextLine();
        promptToContinue();
    }

    private void handleActionWithPause(Runnable action) {
        action.run();
        scanner.nextLine();
        promptToContinue();
    }
}
