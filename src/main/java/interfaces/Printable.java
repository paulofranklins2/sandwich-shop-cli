package interfaces;

import java.io.PrintStream;

@FunctionalInterface
public interface Printable {
    void printSummary(PrintStream out);
}
