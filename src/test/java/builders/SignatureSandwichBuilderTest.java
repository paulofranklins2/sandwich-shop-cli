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

class SignatureSandwichBuilderTest {

    @Test
    void build_createsSandwichFromSignaturePresetWithoutModification() {
        String simulatedInput = String.join("\n", List.of("0", "0", "0"));
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        UserPromptUtils.resetScanner();

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
    }
}
