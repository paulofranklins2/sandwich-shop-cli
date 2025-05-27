package ui;

import builders.ChipBuilder;
import builders.DrinkBuilder;
import builders.SandwichBuilder;
import builders.SignatureSandwichBuilder;
import models.Order;
import persistence.ReceiptManager;

import java.io.IOException;

import static utils.ConsolePrinter.printHeader;
import static utils.UserPromptUtils.*;

/**
 * Handles the command-line interface for the DELI-cious POS system.
 * Lets users place orders, check receipts, and manage their choices.
 */
public class UserInterface {

    private final Order currentOrder = new Order();

    /**
     * Kicks things off by showing the main menu.
     */
    public void init() {
        mainMenu();
    }

    /**
     * Shows the main menu and handles user choices:
     * - Start a new order
     * - Look up an old receipt
     * - Exit the app
     */
    private void mainMenu() {
        while (true) {
            printHeader("Welcome to Sandwich Shop POS");
            System.out.println("[1] - New Order");
            System.out.println("[2] - View Receipt by ID");
            System.out.println("[0] - Exit");

            int option = intPrompt("Choose: ");
            switch (option) {
                case 1 -> orderScreen();
                case 2 -> viewReceiptById();
                case 0 -> {
                    System.out.println("Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid option.");
            }
        }
    }

    /**
     * Asks for a receipt number and tries to load and show it from file.
     */
    private void viewReceiptById() {
        scanner.nextLine();
        printHeader("Retrieve Receipt");
        String id = stringPrompt("Enter your receipt number (e.g., 20250523-172201): ");
        try {
            new ReceiptManager().loadReceiptById(id);
        } catch (IOException e) {
            System.out.println("Receipt not found: " + e.getMessage());
        }
    }

    /**
     * Lets the user build an order step-by-step.
     */
    private void orderScreen() {
        currentOrder.clear();
        while (true) {
            printHeader("Order Screen");
            System.out.println("[1] - Add Sandwich");
            System.out.println("[2] - Add Signature Sandwich");
            System.out.println("[3] - Add Drink");
            System.out.println("[4] - Add Chips");
            System.out.println("[5] - Checkout");
            System.out.println("[0] - Cancel Order");

            int choice = intPrompt("Choose: ");
            switch (choice) {
                case 1 -> currentOrder.addItem(new SandwichBuilder().build());
                case 2 -> currentOrder.addItem(new SignatureSandwichBuilder().build());
                case 3 -> currentOrder.addItem(new DrinkBuilder().build());
                case 4 -> currentOrder.addItem(new ChipBuilder().build());
                case 5 -> {
                    checkoutScreen();
                    return;
                }
                case 0 -> {
                    System.out.println("Order canceled.");
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
            try {
                String receiptNumber = new ReceiptManager().saveOrderReceipt(currentOrder.getItems(), total);
                System.out.println("Order confirmed and saved.");
                System.out.println("📄 Your receipt number: " + receiptNumber);
            } catch (IOException e) {
                System.out.println("Failed to save receipt: " + e.getMessage());
            }
        } else {
            System.out.println("Order canceled.");
        }
    }
}
