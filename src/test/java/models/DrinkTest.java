package models;

import models.enums.DrinkFlavor;
import models.enums.DrinkSize;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class DrinkTest {

    @Test
    void getPrice_returnsCorrectPriceForSmall() {
        Drink drink = new Drink(DrinkSize.SMALL, DrinkFlavor.COCA_COLA);
        assertEquals(0, drink.getPrice().compareTo(new BigDecimal("2.00")));
    }

    @Test
    void getPrice_returnsCorrectPriceForMedium() {
        Drink drink = new Drink(DrinkSize.MEDIUM, DrinkFlavor.SPRITE);
        assertEquals(0, drink.getPrice().compareTo(new BigDecimal("2.50")));
    }

    @Test
    void getPrice_returnsCorrectPriceForLarge() {
        Drink drink = new Drink(DrinkSize.LARGE, DrinkFlavor.FANTA);
        assertEquals(0, drink.getPrice().compareTo(new BigDecimal("3.00")));
    }

    @Test
    void getDrinkSize_returnsCorrectSize() {
        Drink drink = new Drink(DrinkSize.MEDIUM, DrinkFlavor.FANTA);
        assertEquals(DrinkSize.MEDIUM, drink.getDrinkSize());
    }

    @Test
    void getDrinkFlavor_returnsCorrectFlavor() {
        Drink drink = new Drink(DrinkSize.SMALL, DrinkFlavor.SPRITE);
        assertEquals(DrinkFlavor.SPRITE, drink.getDrinkFlavor());
    }

    @Test
    void printSummary_outputsFormattedDrinkDetails() {
        Drink drink = new Drink(DrinkSize.LARGE, DrinkFlavor.COCA_COLA);
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        drink.printSummary(new PrintStream(output));
        String summary = output.toString().trim();

        assertTrue(summary.contains("Coca Cola"), "Expected formatted flavor in output");
        assertTrue(summary.contains("Large"), "Expected formatted size in output");
        assertTrue(summary.contains("$3.00"), "Expected correct price in output");
    }
}
