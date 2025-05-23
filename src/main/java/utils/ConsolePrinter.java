package utils;

public class ConsolePrinter {
    public static void printHeader(String title) {
        System.out.println("\n=== " + title + " ===");
    }

    public static void printLine(String message) {
        System.out.println(message);
    }

    public static void printOption(int index, String label) {
        System.out.printf("[%d] - %s%n", index, label);
    }
}
