package persistence;

import interfaces.MenuItem;
import interfaces.Printable;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ReceiptManager {

    public void saveOrderReceipt(List<MenuItem> orderItems, double total) throws IOException {
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
                    // Capture printed summary
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
    }

    private String formattedDateTime(LocalDateTime dateTime, DateTimeFormatter formatter) {
        return dateTime.format(formatter);
    }
}
