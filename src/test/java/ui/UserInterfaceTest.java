package ui;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.UserPromptUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

class UserInterfaceTest {

    private final InputStream originalIn = System.in;
    private final PrintStream originalOut = System.out;
    private ByteArrayOutputStream testOut;

    @BeforeEach
    void setUp() {
        testOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(testOut));
    }

    @AfterEach
    void tearDown() {
        System.setIn(originalIn);
        System.setOut(originalOut);
    }

    @Test
    void userCanAddDrinkAndExit() {
        String input = String.join("\n", new String[]{
                "1",  // Main Menu: New Order
                "3",  // Order Screen: Add Drink
                "1",  // Drink Size: Medium
                "0",  // Drink Flavor: Coca Cola
                "",   // promptToContinue()
                "0",  // Cancel Order
                "0"   // Exit
        });

        System.setIn(new ByteArrayInputStream(input.getBytes()));
        UserPromptUtils.resetScanner();

        UserInterface ui = new UserInterface();
        ui.init();

        String output = testOut.toString();

        assertTrue(output.contains("Welcome to Sandwich Shop POS"));
        assertTrue(output.contains("Order Screen"));
        assertTrue(output.contains("Add Drink"));
        assertTrue(output.contains("Goodbye!"));
    }

    @Test
    void viewReceiptById_validReceiptId_printsReceipt() {
        String simulatedInput = String.join("\n", List.of(
                "",                            // Scanner .nextLine() skip
                "20250523-165436",             // Receipt ID
                "",                            // Scanner .nextLine() skip after printing
                ""                             // promptToContinue()
        ));

        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        UserPromptUtils.resetScanner();

        UserInterface ui = new UserInterface();
        ui.viewReceiptById();

        String output = testOut.toString();

        assertTrue(output.contains("Steak"), "Expected 'Steak' in receipt output");
        assertTrue(output.contains("Red Bull"), "Expected 'Red Bull' in receipt output");
        assertTrue(output.contains("Takis Fuego"), "Expected 'Takis Fuego' in receipt output");
        assertTrue(output.contains("TOTAL: $41.80"), "Expected correct total in receipt output");
    }
}
