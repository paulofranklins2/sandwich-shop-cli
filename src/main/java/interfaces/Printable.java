package interfaces;

import java.io.PrintStream;

/**
 * Represents an object that can print a formatted summary of its content.
 * Useful for displaying order or item details in receipts or CLI interfaces.
 */
@FunctionalInterface
public interface Printable {

    /**
     * Prints a detailed summary of the object to the provided {@link PrintStream}.
     *
     * @param out the output stream to print to
     */
    void printSummary(PrintStream out);
}
