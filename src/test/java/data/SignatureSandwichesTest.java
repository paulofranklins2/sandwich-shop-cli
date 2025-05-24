package data;

import models.SignatureSandwich;
import models.enums.BreadType;
import models.enums.SandwichSize;
import models.enums.Topping;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SignatureSandwichesTest {

    @Test
    void getAll_returnsNonEmptyListOfSignatureSandwiches() {
        List<SignatureSandwich> sandwiches = SignatureSandwiches.getAll();

        assertNotNull(sandwiches, "The list of signature sandwiches should not be null");
        assertFalse(sandwiches.isEmpty(), "The list of signature sandwiches should not be empty");
    }

    @Test
    void getAll_includesExpectedBLTSandwich() {
        SignatureSandwich blt = SignatureSandwiches.getAll().stream()
                .filter(s -> s.getName().equalsIgnoreCase("BLT"))
                .findFirst()
                .orElse(null);

        assertNotNull(blt, "BLT sandwich should be present");
        assertEquals(SandwichSize.EIGHT_INCH, blt.getSandwichSize());
        assertEquals(BreadType.WHITE, blt.getBreadType());
        assertTrue(blt.getToppings().containsAll(List.of(
                Topping.BACON, Topping.LETTUCE, Topping.TOMATOES
        )));
        assertTrue(blt.getExtraToppings().isEmpty());
        assertTrue(blt.getIsToasted());
    }
}
