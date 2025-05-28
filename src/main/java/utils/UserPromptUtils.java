package utils;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Handy helper for getting user input and making enums look nice in the CLI.
 */
public class UserPromptUtils {
    public static final Scanner scanner = new Scanner(System.in);

    /**
     * Asks the user for a number and keeps trying until they get it right.
     *
     * @param message the message to show the user
     * @return the number they entered
     */
    public static int intPrompt(String message) {
        while (true) {
            try {
                System.out.print(message);
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // clear the bad input
            }
        }
    }

    /**
     * Asks the user for a line of text and trims it.
     * Keeps retrying if something goes wrong.
     *
     * @param prompt the message to show
     * @return the trimmed input
     */
    public static String stringPrompt(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return scanner.nextLine().trim();
            } catch (Exception e) {
                System.out.println("Invalid input. Please try again.");
            }
        }
    }

    /**
     * Shows a list of choices and asks the user to pick one.
     *
     * @param title   the message above the list
     * @param options the things they can pick from
     * @param <T>     whatever type's things are
     * @return the option they picked
     */
    public static <T> T promptOption(String title, T[] options) {
        // Show the title above the list
        System.out.println("\n" + title);

        for (int i = 0; i < options.length; i++) {
            // Format the option nicely for display
            String label = capitalizeWords(options[i].toString().replace("_", " ").toLowerCase());
            // Print each option with a number
            System.out.printf("[%d] - %s%n", i, label);
        }

        // Ask until the user enters a valid number
        while (true) {
            int input = intPrompt("Choose: ");
            if (input >= 0 && input < options.length) {
                return options[input]; // Return the selected option
            }
            // If the input is not valid, show a helpful message
            System.out.println("Invalid option. Please pick a number between 0 and " + (options.length - 1) + ".");
        }
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
     * Takes a list of enums and turns them into a nice, comma-separated string.
     *
     * @param list the enum list
     * @param <T>  the enum type
     * @return a user-friendly string of the values
     */
    public static <T extends Enum<T>> String formatEnumList(List<T> list) {
        return list.stream()
                .map(e -> capitalizeWords(e.toString().replace("_", " ").toLowerCase()))
                .collect(Collectors.joining(", "));
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
     * Prints a bunch of empty lines to simulate clearing the console.
     * It doesn’t clear the screen but gives a similar effect.
     */
    public static void clearScreen() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }

    /**
     * Pauses until the user hits Enter, then "clears" the console.
     */
    public static void promptToContinue() {
        System.out.print("Press Enter to continue... ");
        scanner.nextLine();
        clearScreen();
    }
}