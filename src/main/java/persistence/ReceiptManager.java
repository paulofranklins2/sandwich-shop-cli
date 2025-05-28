package persistence;

import interfaces.MenuItem;
import interfaces.Printable;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static utils.UserPromptUtils.*;

/**
 * Handles saving order receipts to files.
 * Makes a file with a timestamp and the full order details + total.
 */
public class ReceiptManager {
    private final String path = "src/main/resources/receipt/";

    /**
     * Saves the order to a file, listing all the items and the total price.
     *
     * @param orderItems list of everything in the order
     * @param total      the total price for the order
     * @return the name of the file the receipt was saved to
     * @throws IOException if something goes wrong while writing the file
     */
    public String saveOrderReceipt(List<MenuItem> orderItems, double total) throws IOException {
        // Use the current date and time to make the file name unique
        LocalDateTime now = LocalDateTime.now();
        String fileName = now.format(DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss"));

        // Write the receipt to the file
        try (PrintStream printStream = new PrintStream(new FileOutputStream(path + fileName))) {
            printStream.println("===============  Order Receipt =============== ");
            printStream.println("Date: " + now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            printStream.println();

            for (MenuItem item : orderItems) {
                if (item instanceof Printable printable) {
                    printable.printSummary(printStream);
                } else {
                    printStream.println("Unprintable item: " + item.getClass().getSimpleName());
                }
            }

            printStream.println();
            printStream.printf("TOTAL: $%.2f%n", total);
        }
        return fileName;
    }

    /**
     * Loads and prints a receipt file by its ID.
     * Looks for the file in 'src/main/resources/receipt/' and prints it to the console.
     *
     * @param receiptId name of the receipt file (like "20250523-172201")
     * @throws IOException if the file can't be found or read
     */
    public void loadReceiptById(String receiptId) throws IOException {
        String fullPath = path + receiptId;

        try (var reader = new java.util.Scanner(new java.io.File(fullPath))) {
            System.out.println("=== Receipt: " + receiptId + " ===");
            while (reader.hasNextLine()) {
                System.out.println(reader.nextLine());
            }
        } catch (IOException e) {
            throw new IOException("Couldn't find the receipt with ID: " + receiptId);
        }
    }
}
