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
        int headerWidth = 50; // Total width for the header line
        int paddingLeft = (headerWidth - title.length()) / 2;

        String line = "—".repeat(headerWidth);
        String centeredTitle = " ".repeat(Math.max(0, paddingLeft)) + title;

        System.out.println(line);
        System.out.println(centeredTitle);
        System.out.println(line);
    }
}
