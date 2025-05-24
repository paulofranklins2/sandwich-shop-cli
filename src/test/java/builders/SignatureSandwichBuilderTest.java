package builders;

import models.Sandwich;
import models.enums.BreadType;
import models.enums.SandwichSize;
import models.enums.Topping;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SignatureSandwichBuilderTest {

    @Test
    void build_createsSandwichFromSignaturePresetWithoutModification() {
        // Simulate the following input:
        // 0 - Choose first signature sandwich (BLT)
        // 0 - Would you like to remove any toppings? No
        // 0 - Would you like to add any toppings? No
        String simulatedInput = String.join("\n", List.of(
                "0",  // Choose BLT
                "0",  // No topping removal
                "0"   // No topping addition
        ));

        InputStream originalIn = System.in;
        try {
            System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

            SignatureSandwichBuilder builder = new SignatureSandwichBuilder();
            Sandwich sandwich = builder.build();

            assertNotNull(sandwich);
            assertEquals(SandwichSize.EIGHT_INCH, sandwich.getSandwichSize());
            assertEquals(BreadType.WHITE, sandwich.getBreadType());
            assertTrue(sandwich.getToppings().containsAll(List.of(
                    Topping.BACON, Topping.LETTUCE, Topping.TOMATOES
            )));
            assertTrue(sandwich.getExtraToppings().isEmpty());
            assertTrue(sandwich.getIsToasted());

        } finally {
            System.setIn(originalIn); // Restore System.in
        }
    }
}
