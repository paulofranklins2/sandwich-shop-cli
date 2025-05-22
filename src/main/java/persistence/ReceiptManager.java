package persistence;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ReceiptManager {
    private final String PATH = "src/main/resources/receipt/";

    public void saveOrderReceipt() throws IOException {
        // Get the current local date and time
        LocalDateTime localDateTime = LocalDateTime.now();

        // Define the date-time format as "yyyyMMdd-HHmmss" (e.g., 20250522-153012)
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss");

        // Format the current date and time into a string
        String formattedDateTime = localDateTime.format(formatter);

        // Attempt to write to a file named with the formatted timestamp
        // If the file doesn't exist, it will be created
        try (PrintWriter printWriter = new PrintWriter(new FileWriter(PATH + formattedDateTime))) {
            // TODO: Replace placeholder with actual receipt content once receipt generation is implemented
            printWriter.println("ASDFASDF");
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }
    }
}
