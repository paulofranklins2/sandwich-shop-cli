package models;

import models.enums.BreadType;
import models.enums.SandwichSize;
import models.enums.Topping;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the {@link SignatureSandwich} class.
 */
class SignatureSandwichTest {

    @Test
    void constructor_shouldStoreAllFieldsCorrectly() {
        SignatureSandwich sandwich = new SignatureSandwich(
                "BLT",
                SandwichSize.EIGHT_INCH,
                BreadType.WHITE,
                List.of(Topping.BACON, Topping.LETTUCE, Topping.TOMATOES),
                List.of(),
                true
        );

        assertEquals("BLT", sandwich.getName());
        assertEquals(SandwichSize.EIGHT_INCH, sandwich.getSandwichSize());
        assertEquals(BreadType.WHITE, sandwich.getBreadType());
        assertEquals(3, sandwich.getToppings().size());
        assertTrue(sandwich.getExtraToppings().isEmpty());
        assertTrue(sandwich.getIsToasted());
    }

    @Test
    void getPrice_shouldReturnCorrectTotal() {
        SignatureSandwich sandwich = new SignatureSandwich(
                "Test Sandwich",
                SandwichSize.FOUR_INCH,
                BreadType.RYE,
                List.of(Topping.HAM, Topping.CHEDDAR),
                List.of(Topping.HAM),
                false
        );

        BigDecimal expectedPrice = BigDecimal.valueOf(5.50) // base
                .add(BigDecimal.valueOf(1.00))               // HAM
                .add(BigDecimal.valueOf(0.75))               // CHEDDAR
                .add(BigDecimal.valueOf(0.50));              // extra HAM

        assertEquals(expectedPrice, sandwich.getPrice());
    }

    @Test
    void printSummary_shouldIncludeSandwichNameAndDetails() {
        SignatureSandwich sandwich = new SignatureSandwich(
                "Philly Cheesesteak",
                SandwichSize.TWELVE_INCH,
                BreadType.ITALIAN,
                List.of(Topping.STEAK, Topping.AMERICAN),
                List.of(),
                true
        );

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        sandwich.printSummary(new PrintStream(out));
        String result = out.toString();

        assertTrue(result.contains("Signature Sandwich: Philly Cheesesteak"));
        assertTrue(result.contains("Steak"));
        assertTrue(result.contains("American"));
        assertTrue(result.contains("Toasted: Yes"));
        assertTrue(result.contains("12\""));
    }
}
