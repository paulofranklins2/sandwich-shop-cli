package utils;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Utility class for handling user input and formatting enum values in the CLI.
 */
public class UserInputUtils {
    public static final Scanner scanner = new Scanner(System.in);

    /**
     * Prompts the user for an integer value with the given message.
     * Keeps retrying until a valid integer is entered.
     *
     * @param message the prompt message
     * @return the integer entered by the user
     */
    public static int intPrompt(String message) {
        while (true) {
            try {
                System.out.print(message);
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // clear the invalid input
            }
        }
    }

    /**
     * Displays a list of options and prompts the user to select one.
     * Accepts any array of objects and returns the selected value.
     *
     * @param title   the message describing the option group
     * @param options the available options
     * @param <T>     the type of option
     * @return the selected option
     */
    public static <T> T promptOption(String title, T[] options) {
        System.out.println("\n" + title);
        for (int i = 0; i < options.length; i++) {
            String label = capitalizeWords(options[i].toString().replace("_", " ").toLowerCase());
            System.out.printf("[%d] - %s%n", i, label);
        }

        while (true) {
            int input = intPrompt("Choose: ");
            if (input >= 0 && input < options.length) {
                return options[input];
            }
            System.out.println("Invalid option. Please enter a number between 0 and " + (options.length - 1) + ".");
        }
    }

    /**
     * Capitalizes each word in a given string.
     *
     * @param input the string to be capitalized
     * @return a capitalized version of the input
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
     * Formats a list of enums into a user-friendly string (capitalized and separated by commas).
     *
     * @param list the list of enum values
     * @param <T>  the enum type
     * @return a formatted string of capitalized enum names
     */
    public static <T extends Enum<T>> String formatEnumList(List<T> list) {
        return list.stream()
                .map(e -> capitalizeWords(e.toString().replace("_", " ").toLowerCase()))
                .collect(Collectors.joining(", "));
    }

    /**
     * Formats a single enum value into a user-friendly capitalized string.
     *
     * @param e   the enum value
     * @param <T> the enum type
     * @return a formatted string representation of the enum
     */
    public static <T extends Enum<T>> String formatEnum(T e) {
        return capitalizeWords(e.toString().replace("_", " ").toLowerCase());
    }
}
