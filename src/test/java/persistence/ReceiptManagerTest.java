package persistence;

import interfaces.MenuItem;
import models.Drink;
import models.enums.DrinkFlavor;
import models.enums.DrinkSize;
import org.junit.jupiter.api.*;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the {@link ReceiptManager} class.
 */
class ReceiptManagerTest {

    private ReceiptManager receiptManager;
    private static final String RECEIPT_DIR = "src/main/resources/receipt/";

    @BeforeEach
    void setUp() {
        receiptManager = new ReceiptManager();
        new File(RECEIPT_DIR); // Ensure the receipt directory exists
    }

    @Test
    void saveOrderReceipt_createsReceiptFileWithCorrectContent() throws IOException {
        var drink = new Drink(DrinkSize.LARGE, DrinkFlavor.COCA_COLA);
        List<MenuItem> items = List.of(drink);
        double total = drink.getPrice().doubleValue();

        ReceiptManager receiptManager = new ReceiptManager();
        String receiptId = receiptManager.saveOrderReceipt(items, total);

        File receiptFile = new File("src/main/resources/receipt/" + receiptId);
        assertTrue(receiptFile.exists(), "Receipt file should exist");

        String content = Files.readString(receiptFile.toPath());
        assertTrue(content.contains("Drink"), "Receipt should mention Drink");
        assertTrue(content.contains("TOTAL: $3.00"), "Receipt should show total price correctly");

        // Clean up
        receiptFile.delete();
    }


    @Test
    void loadReceiptById_printsReceiptContents() throws IOException {
        // Prepare a receipt file to load
        String receiptId = "test-receipt.txt";
        File testReceipt = new File(RECEIPT_DIR + receiptId);
        Files.writeString(testReceipt.toPath(), "=== Order Receipt ===\nMock Content\nTOTAL: $5.00\n");

        assertDoesNotThrow(() -> receiptManager.loadReceiptById(receiptId));
        // Clean up
        testReceipt.delete();
    }

    @Test
    void loadReceiptById_throwsIOExceptionForMissingFile() {
        String missingReceiptId = "non-existent-receipt.txt";
        Exception exception = assertThrows(IOException.class, () -> {
            receiptManager.loadReceiptById(missingReceiptId);
        });
        assertTrue(exception.getMessage().contains("Couldn't find the receipt with ID: " + missingReceiptId));
    }
}
