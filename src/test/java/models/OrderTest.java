package models;

import models.enums.BreadType;
import models.enums.ChipFlavor;
import models.enums.DrinkFlavor;
import models.enums.DrinkSize;
import models.enums.SandwichSize;
import models.enums.Topping;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the {@link Order} class.
 */
class OrderTest {

    private Order order;

    @BeforeEach
    void setUp() {
        order = new Order();
    }

    @Test
    void newOrder_shouldBeEmpty() {
        assertTrue(order.isEmpty());
        assertEquals(0, order.getPrice().compareTo(BigDecimal.ZERO));
    }

    @Test
    void addItem_shouldAddSandwichToOrder() {
        Sandwich sandwich = new Sandwich(SandwichSize.FOUR_INCH, BreadType.WHITE, List.of(Topping.HAM), List.of(), false);
        order.addItem(sandwich);

        assertFalse(order.isEmpty());
        assertEquals(0, order.getPrice().compareTo(sandwich.getPrice()));
    }

    @Test
    void addItem_shouldAddMultipleItemsAndSumPrice() {
        Sandwich sandwich = new Sandwich(SandwichSize.FOUR_INCH, BreadType.WHITE, List.of(Topping.HAM), List.of(), false);
        Drink drink = new Drink(DrinkSize.SMALL, DrinkFlavor.COCA_COLA);
        Chip chip = new Chip(ChipFlavor.DORITOS_NACHO_CHEESE);

        order.addItem(sandwich);
        order.addItem(drink);
        order.addItem(chip);

        BigDecimal expected = sandwich.getPrice().add(drink.getPrice()).add(chip.getPrice());
        assertEquals(0, order.getPrice().compareTo(expected));
    }

    @Test
    void clear_shouldRemoveAllItems() {
        order.addItem(new Drink(DrinkSize.SMALL, DrinkFlavor.SPRITE));
        order.clear();

        assertTrue(order.isEmpty());
        assertEquals(0, order.getPrice().compareTo(BigDecimal.ZERO));
    }

    @Test
    void printSummary_shouldOutputFormattedSummary() {
        order.addItem(new Chip(ChipFlavor.LAYS_CLASSIC));

        ByteArrayOutputStream output = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(output);
        order.printSummary(printStream);

        String outputText = output.toString();
        assertTrue(outputText.contains("Order Summary"));
        assertTrue(outputText.contains("Chips: Lays Classic"));
    }
}
