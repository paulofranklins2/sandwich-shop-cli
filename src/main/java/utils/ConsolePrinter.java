package utils;

/**
 * Utility class to simplify and standardize console output formatting.
 * Provides methods for printing headers, lines, and options in a consistent style.
 */
public class ConsolePrinter {

    /**
     * Prints a formatted header with surrounding lines.
     *
     * @param title the title text to display as a section header
     */
    public static void printHeader(String title) {
        System.out.println("\n=== " + title + " ===");
    }

    /**
     * Prints a plain message line to the console.
     *
     * @param message the message to print
     */
    public static void printLine(String message) {
        System.out.println(message);
    }

    /**
     * Prints a selectable option formatted with an index and label.
     *
     * @param index the numeric value of the option
     * @param label the description of the option
     */
    public static void printOption(int index, String label) {
        System.out.printf("[%d] - %s%n", index, label);
    }
}
