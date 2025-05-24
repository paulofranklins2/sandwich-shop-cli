package builders;

import models.Drink;
import models.enums.DrinkFlavor;
import models.enums.DrinkSize;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

class DrinkBuilderTest {

    @Test
    void build_returnsCorrectDrinkBasedOnUserInput() {
        // Backup original System.in
        InputStream originalIn = System.in;

        try {
            // Simulate choosing SMALL (index 0) and COCA_COLA (index 0)
            String simulatedInput = "0\n0\n";
            ByteArrayInputStream input = new ByteArrayInputStream(simulatedInput.getBytes());
            System.setIn(input);

            DrinkBuilder builder = new DrinkBuilder();
            Drink drink = builder.build();

            assertNotNull(drink);
            assertEquals(DrinkSize.SMALL, drink.getDrinkSize());
            assertEquals(DrinkFlavor.COCA_COLA, drink.getDrinkFlavor());

        } finally {
            // Always restore System.in
            System.setIn(originalIn);
        }
    }
}
