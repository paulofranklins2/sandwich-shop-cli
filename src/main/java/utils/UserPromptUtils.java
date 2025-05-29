package utils;

import java.util.InputMismatchException;
import java.util.Scanner;

import static utils.ConsolePrinter.*;

/**
 * Handy helper for getting user input and making enums look nice in the CLI.
 */
public class UserPromptUtils {
    public static Scanner scanner = new Scanner(System.in);

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
        printHeader(title);

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
            // If the input is not valid
            System.out.println("Invalid option. Please pick a number between 0 and " + (options.length - 1) + ".");
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

    public static void resetScanner() {
        scanner.close();
        // Create a new scanner based on the current System.in
        scanner = new Scanner(System.in);
    }
}