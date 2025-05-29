package models;

import models.enums.BreadType;
import models.enums.SandwichSize;
import models.enums.Topping;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit tests for the {@link Sandwich} class.
 */
class SandwichTest {

    @Test
    void getPrice_shouldReturnBasePriceOnly_whenNoToppings() {
        Sandwich sandwich = new Sandwich(SandwichSize.FOUR_INCH, BreadType.WHITE, List.of(), List.of(), false);
        assertEquals(BigDecimal.valueOf(5.50), sandwich.getPrice());
    }

    @Test
    void getPrice_shouldIncludeMeatAndCheeseToppings() {
        Sandwich sandwich = new Sandwich(
                SandwichSize.EIGHT_INCH,
                BreadType.ITALIAN,
                List.of(Topping.HAM, Topping.CHEDDAR),
                List.of(),
                true
        );

        BigDecimal expected = BigDecimal.valueOf(7.00) // base
                .add(BigDecimal.valueOf(2.00))          // HAM (meat)
                .add(BigDecimal.valueOf(1.50));         // CHEDDAR (cheese)

        assertEquals(expected, sandwich.getPrice());
    }

    @Test
    void getPrice_shouldIncludeExtraMeatAndCheese() {
        Sandwich sandwich = new Sandwich(
                SandwichSize.TWELVE_INCH,
                BreadType.RYE,
                List.of(),
                List.of(Topping.BACON, Topping.SWISS),
                false
        );

        BigDecimal expected = BigDecimal.valueOf(8.50)     // base
                .add(BigDecimal.valueOf(1.50))              // BACON (extra meat)
                .add(BigDecimal.valueOf(0.90));             // SWISS (extra cheese)

        assertEquals(expected, sandwich.getPrice());
    }

    @Test
    void printSummary_shouldIncludeSandwichDetails() {
        Sandwich sandwich = new Sandwich(
                SandwichSize.FOUR_INCH,
                BreadType.WRAP,
                List.of(Topping.HAM, Topping.CHEDDAR),
                List.of(Topping.CHEDDAR),
                true
        );

        ByteArrayOutputStream output = new ByteArrayOutputStream();
        sandwich.printSummary(new PrintStream(output));
        String result = output.toString();

        assertTrue(result.contains("Sandwich:"));
        assertTrue(result.contains("4\""));
        assertTrue(result.contains("Wrap"));
        assertTrue(result.contains("Toasted: Yes"));
        assertTrue(result.contains("Ham"));
        assertTrue(result.contains("Cheddar"));
        assertTrue(result.contains("Price:"));
    }
}
