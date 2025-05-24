package models;

import interfaces.MenuItem;
import interfaces.Printable;
import lombok.Getter;
import models.enums.DrinkFlavor;
import models.enums.DrinkSize;

import java.io.PrintStream;
import java.math.BigDecimal;

import static utils.UserInputUtils.capitalizeWords;

/**
 * Represents a drink item that can be added to an order.
 * A drink has a specific {@link DrinkSize} and {@link DrinkFlavor},
 * and its price is determined by its size.
 */
@Getter
public class Drink implements MenuItem, Printable {
    private final DrinkSize drinkSize;
    private final DrinkFlavor drinkFlavor;

    /**
     * Constructs a Drink with the specified size and flavor.
     *
     * @param drinkSize   the selected size of the drink
     * @param drinkFlavor the selected flavor of the drink
     */
    public Drink(DrinkSize drinkSize, DrinkFlavor drinkFlavor) {
        this.drinkSize = drinkSize;
        this.drinkFlavor = drinkFlavor;
    }

    /**
     * Returns the price of the drink based on its {@link DrinkSize}.
     *
     * @return the drink price as a {@link BigDecimal}
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
     * Prints a formatted summary of the drink, including its flavor, size, and price.
     *
     * @param print the output stream to print to
     */
    @Override
    public void printSummary(PrintStream print) {
        String flavor = capitalizeWords(drinkFlavor.toString().replace("_", " ").toLowerCase());
        String size = capitalizeWords(drinkSize.toString().replace("_", " ").toLowerCase());
        print.printf("Drink: %s (%s) - $%.2f%n", flavor, size, getPrice());
    }
}
