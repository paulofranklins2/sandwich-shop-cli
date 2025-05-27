package interfaces;

import java.io.PrintStream;

/**
 * Anything that can print out a nice summary of itself.
 * Handy for showing order or item info in receipts or the CLI.
 */
@FunctionalInterface
public interface Printable {

    /**
     * Prints a summary of this object to the given {@link PrintStream}.
     *
     * @param out where to print the summary
     */
    void printSummary(PrintStream out);
}
