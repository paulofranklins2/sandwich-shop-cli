package utils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Handy little helper for printing stuff to the console in a clean way.
 * Focuses on formatting output like headers, spacing, and enum labels
 * to keep things looking consistent and user-friendly.
 */
public class ConsolePrinter {

    /**
     * Prints a header with lines around it.
     *
     * @param title the title to show at the top of a section
     */
    public static void printHeader(String title) {
        // Total width for the header line
        int headerWidth = 50;
        int paddingLeft = (headerWidth - title.length()) / 2;

        String line = "—".repeat(headerWidth);
        String centeredTitle = " ".repeat(Math.max(0, paddingLeft)) + title;

        System.out.println(line);
        System.out.println(centeredTitle);
        System.out.println(line);
    }

    /**
     * Prints a bunch of empty lines to simulate clearing the console.
     * It doesn’t clear the screen but gives a similar effect.
     */
    public static void clearScreen() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }

    /**
     * Formats a single enum to look nice (capitalized and spaced out).
     *
     * @param e   the enum value
     * @param <T> the enum type
     * @return a cleaner-looking string
     */
    public static <T extends Enum<T>> String formatEnum(T e) {
        return capitalizeWords(e.toString().replace("_", " ").toLowerCase());
    }

    /**
     * Takes a list of enums and turns them into a nice, comma-separated string.
     *
     * @param list the enum list
     * @param <T>  the enum type
     * @return a user-friendly string of the values
     */
    public static <T extends Enum<T>> String formatEnumList(List<T> list) {
        return list.stream()
                .map(e ->
                        capitalizeWords(e.toString()
                                .replace("_", " ")
                                .toLowerCase()))
                .collect(Collectors.joining(", "));
    }

    /**
     * Capitalizes each word in a string.
     *
     * @param input the string to change
     * @return the same string, just with capital letters at the start of each word
     */
    public static String capitalizeWords(String input) {
        String[] words = input.split(" ");
        StringBuilder builder = new StringBuilder();
        for (String word : words) {
            if (!word.isEmpty()) {
                builder.append(Character.toUpperCase(word.charAt(0)))
                        .append(word.substring(1))
                        .append(" ");
            }
        }
        return builder.toString().trim();
    }

    /**
     * Prints the main menu options for the user to start a new order,
     */
    public static void printMainMenu() {
        printHeader("Welcome to Sandwich Shop POS");
        System.out.println("[1] - New Order");
        System.out.println("[2] - View Receipt by ID");
        System.out.println("[0] - Exit");
    }

    /**
     * Prints the order screen menu options for building an order,
     */
    public static void printOrderMenu() {
        printHeader("Order Screen");
        System.out.println("[1] - Add Sandwich");
        System.out.println("[2] - Add Signature Sandwich");
        System.out.println("[3] - Add Drink");
        System.out.println("[4] - Add Chips");
        System.out.println("[5] - Checkout");
        System.out.println("[0] - Cancel Order");
    }
}