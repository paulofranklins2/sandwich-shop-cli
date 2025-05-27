package models;

import interfaces.MenuItem;
import interfaces.Printable;
import lombok.Getter;
import models.enums.DrinkFlavor;
import models.enums.DrinkSize;

import java.io.PrintStream;
import java.math.BigDecimal;

import static utils.UserPromptUtils.capitalizeWords;

/**
 * A drink you can add to an order.
 * Has a {@link DrinkSize} and {@link DrinkFlavor}, and the price depends on the size.
 */
@Getter
public class Drink implements MenuItem, Printable {
    private final DrinkSize drinkSize;
    private final DrinkFlavor drinkFlavor;

    /**
     * Makes a Drink with the given size and flavor.
     *
     * @param drinkSize   the size the user picked
     * @param drinkFlavor the flavor the user picked
     */
    public Drink(DrinkSize drinkSize, DrinkFlavor drinkFlavor) {
        this.drinkSize = drinkSize;
        this.drinkFlavor = drinkFlavor;
    }

    /**
     * Figures out the price based on the drink’s size.
     *
     * @return the price as a {@link BigDecimal}
     */
    @Override
    public BigDecimal getPrice() {
        return switch (drinkSize) {
            case SMALL -> BigDecimal.valueOf(2.00);
            case MEDIUM -> BigDecimal.valueOf(2.50);
            case LARGE -> BigDecimal.valueOf(3.00);
        };
    }

    /**
     * Prints out the drink’s size, flavor, and price.
     *
     * @param print where to print the summary
     */
    @Override
    public void printSummary(PrintStream print) {
        String flavor = capitalizeWords(drinkFlavor.toString().replace("_", " ").toLowerCase());
        String size = capitalizeWords(drinkSize.toString().replace("_", " ").toLowerCase());
        print.printf("Drink: %s (%s) - $%.2f%n", flavor, size, getPrice());
    }
}