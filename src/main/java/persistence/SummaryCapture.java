package persistence;

import java.io.PrintStream;
import java.io.ByteArrayOutputStream;

public class SummaryCapture extends PrintStream {
    private final ByteArrayOutputStream content;

    public SummaryCapture() {
        super(new ByteArrayOutputStream());
        this.content = (ByteArrayOutputStream) this.out;
    }

    @Override
    public String toString() {
        return content.toString();
    }
}
