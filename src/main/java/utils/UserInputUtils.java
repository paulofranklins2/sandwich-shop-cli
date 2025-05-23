package utils;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class UserInputUtils {
    public static final Scanner scanner = new Scanner(System.in);

    public static int intPrompt(String message) {
        while (true) {
            try {
                System.out.print(message);
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number. ");
                scanner.nextLine();
            }
        }
    }

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

    public static <T extends Enum<T>> String formatEnumList(List<T> list) {
        return list.stream()
                .map(e -> capitalizeWords(e.toString().replace("_", " ").toLowerCase()))
                .collect(Collectors.joining(", "));
    }

    public static <T extends Enum<T>> String formatEnum(T e) {
        return capitalizeWords(e.toString().replace("_", " ").toLowerCase());
    }
}
