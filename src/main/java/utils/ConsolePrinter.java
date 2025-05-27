package utils;

/**
 * Handy little helper for printing stuff to the console in a clean way.
 * Helps keep headers, lines, and options looking consistent.
 */
public class ConsolePrinter {

    /**
     * Prints a bold header with lines around it.
     *
     * @param title the title to show at the top of a section
     */
    public static void printHeader(String title) {
        System.out.println("=============== " + title + " ===============");
    }

    /**
     * Prints a menu option like [1] - Option Name.
     *
     * @param index number for the option
     * @param label what is the option
     */
    public static void printOption(int index, String label) {
        System.out.printf("[%d] - %s%n", index, label);
    }
}
