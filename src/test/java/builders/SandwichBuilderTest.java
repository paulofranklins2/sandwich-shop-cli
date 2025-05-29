package builders;

import models.Sandwich;
import models.enums.BreadType;
import models.enums.SandwichSize;
import models.enums.Topping;
import org.junit.jupiter.api.Test;
import utils.UserPromptUtils;

import java.io.ByteArrayInputStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SandwichBuilderTest {

    @Test
    void build_createsValidSandwichFromSimulatedUserInput() {
        String simulatedInput = String.join("\n", List.of(
                "0",  // Bread: White
                "0",  // Size: 4"
                "1",  // Add toppings? Yes
                "0",  // Topping type: Meat
                "5",  // Select Bacon
                "0",  // Extra? No
                "0",  // Add more? No
                "0",  // Remove toppings? No
                "1"   // Toast? Yes
        ));

        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        UserPromptUtils.resetScanner(); // clean reset after test

        SandwichBuilder builder = new SandwichBuilder();
        Sandwich sandwich = builder.build();

        assertNotNull(sandwich);
        assertEquals(BreadType.WHITE, sandwich.getBreadType());
        assertEquals(SandwichSize.FOUR_INCH, sandwich.getSandwichSize());
        assertTrue(sandwich.getToppings().contains(Topping.BACON));
        assertTrue(sandwich.getExtraToppings().isEmpty());
        assertTrue(sandwich.getIsToasted());
    }
}
