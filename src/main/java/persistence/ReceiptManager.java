package persistence;

import interfaces.MenuItem;
import interfaces.Printable;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Responsible for persisting order receipts to the filesystem.
 * Generates a uniquely timestamped file containing the full order summary and total.
 */
public class ReceiptManager {

    /**
     * Saves a formatted receipt for the given list of {@link MenuItem} entries and total amount.
     * Each item is printed using its {@link Printable} interface if supported.
     *
     * @param orderItems the list of items in the order
     * @param total      the total amount of the order
     * @throws IOException if the file cannot be written
     */
    public String saveOrderReceipt(List<MenuItem> orderItems, double total) throws IOException {
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss");
        String fileName = formattedDateTime(localDateTime, formatter);
        String PATH = "src/main/resources/receipt/";

        try (PrintWriter printWriter = new PrintWriter(new FileWriter(PATH + fileName))) {
            printWriter.println("=== Order Receipt ===");
            printWriter.println("Date: " + localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            printWriter.println();

            for (MenuItem item : orderItems) {
                if (item instanceof Printable printable) {
                    SummaryCapture summary = new SummaryCapture();
                    printable.printSummary(summary);
                    printWriter.print(summary.toString());
                } else {
                    printWriter.println("Unprintable item: " + item.getClass().getSimpleName());
                }
            }

            printWriter.println();
            printWriter.printf("TOTAL: $%.2f%n", total);
        }
        return fileName; // Receipt number
    }

    /**
     * Formats the current date and time to generate a filename-friendly timestamp.
     *
     * @param dateTime  the current date and time
     * @param formatter the formatter used to shape the timestamp
     * @return a formatted timestamp string
     */
    private String formattedDateTime(LocalDateTime dateTime, DateTimeFormatter formatter) {
        return dateTime.format(formatter);
    }

    /**
     * Loads and displays the contents of a receipt file based on the provided receipt ID.
     * This method reads from the 'src/main/resources/receipt/' directory and prints the
     * receipt to the console. If the file is not found, an IOException is thrown.
     *
     * @param receiptId the filename of the receipt (e.g., "20250523-172201")
     * @throws IOException if the receipt file cannot be found or read
     */
    public void loadReceiptById(String receiptId) throws IOException {
        String PATH = "src/main/resources/receipt/";
        String fullPath = PATH + receiptId;

        try (var reader = new java.util.Scanner(new java.io.File(fullPath))) {
            System.out.println("=== Receipt: " + receiptId + " ===");
            while (reader.hasNextLine()) {
                System.out.println(reader.nextLine());
            }
        } catch (IOException e) {
            throw new IOException("Unable to locate receipt with ID: " + receiptId + ". Please ensure the ID is correct and try again.");
        }
    }
}
