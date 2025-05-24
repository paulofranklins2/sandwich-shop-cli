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

class SandwichBuilderTest {

    @Test
    void build_createsValidSandwichFromSimulatedUserInput() {
        // Simulate user input
        // Indexes may vary depending on enum order
        String simulatedInput = String.join("\n", List.of(
                "0", // BreadType.WHITE
                "0", // SandwichSize.FOUR_INCH
                "1", // Add toppings? Yes
                "0", // ToppingType.MEAT
                "5", // Select BACON (verify correct index!)
                "0", // Add extra? No
                "0", // Add more toppings? No
                "1"  // Toast sandwich? Yes
        ));

        InputStream originalIn = System.in;
        try {
            System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

            SandwichBuilder builder = new SandwichBuilder();
            Sandwich sandwich = builder.build();

            assertNotNull(sandwich);
            assertEquals(BreadType.WHITE, sandwich.getBreadType());
            assertEquals(SandwichSize.FOUR_INCH, sandwich.getSandwichSize());
            assertTrue(sandwich.getToppings().contains(Topping.BACON));
            assertTrue(sandwich.getExtraToppings().isEmpty());
            assertTrue(sandwich.getIsToasted());

        } finally {
            System.setIn(originalIn); // Always restore System.in
        }
    }
}
