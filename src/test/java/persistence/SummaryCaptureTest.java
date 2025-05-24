package persistence;

import org.junit.jupiter.api.Test;

import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit tests for {@link SummaryCapture}, which captures printed output.
 */
class SummaryCaptureTest {

    @Test
    void toString_shouldCapturePrintedContent() {
        // Arrange
        SummaryCapture capture = new SummaryCapture();
        PrintStream out = new PrintStream(capture);

        // Act
        out.println("Hello, Summary!");
        out.print("Line two.");
        String result = capture.toString();

        // Assert
        assertTrue(result.contains("Hello, Summary!"), "Should contain printed line");
        assertTrue(result.contains("Line two."), "Should contain appended line");
    }

    @Test
    void toString_shouldBeEmptyWhenNothingPrinted() {
        // Arrange
        SummaryCapture capture = new SummaryCapture();

        // Act & Assert
        assertEquals("", capture.toString(), "Should be empty when nothing is printed");
    }
}
