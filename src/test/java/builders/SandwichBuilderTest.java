package builders;

import models.Sandwich;
import models.enums.BreadType;
import models.enums.SandwichSize;
import models.enums.Topping;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SandwichBuilderTest {

    private InputStream originalIn;

    @BeforeEach
    void setUp() {
        originalIn = System.in;
    }

    @AfterEach
    void tearDown() {
        System.setIn(originalIn);
    }

    @Test
    void build_createsValidSandwichFromSimulatedUserInput() {
        String simulatedInput = String.join("\n", List.of(
                "0",  // Choose bread: White
                "0",  // Choose size: 4"
                "1",  // Add toppings: Yes
                "0",  // Topping category: Meat
                "5",  // Topping: Bacon
                "0",  // Add extra? No
                "0",  // Add more toppings? No
                "0",  // Remove toppings? No
                "1"   // Toast? Yes
        ));

        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        SandwichBuilder builder = new SandwichBuilder();
        System.out.println(builder);
        Sandwich sandwich = builder.build();

        assertNotNull(sandwich);
        assertEquals(BreadType.WHITE, sandwich.getBreadType());
        assertEquals(SandwichSize.FOUR_INCH, sandwich.getSandwichSize());
        assertTrue(sandwich.getToppings().contains(Topping.BACON));
        assertTrue(sandwich.getExtraToppings().isEmpty());
        assertTrue(sandwich.getIsToasted());
    }
}
