package persistence;

import java.io.PrintStream;
import java.io.ByteArrayOutputStream;

/**
 * A utility PrintStream used to capture output written to it,
 * typically used to intercept {@link interfaces.Printable#printSummary(PrintStream)} output
 * and convert it to a String for file writing or testing.
 */
public class SummaryCapture extends PrintStream {

    private final ByteArrayOutputStream content;

    /**
     * Constructs a new {@code SummaryCapture} that writes to an internal buffer.
     * The output can later be retrieved using {@link #toString()}.
     */
    public SummaryCapture() {
        super(new ByteArrayOutputStream());
        this.content = (ByteArrayOutputStream) this.out;
    }

    /**
     * Returns the content written to this PrintStream as a string.
     *
     * @return the captured output as a string
     */
    @Override
    public String toString() {
        return content.toString();
    }
}
