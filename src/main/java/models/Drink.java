package models;

import interfaces.MenuItem;
import interfaces.Printable;
import models.enums.DrinkFlavor;
import models.enums.DrinkSize;

import java.io.PrintStream;
import java.math.BigDecimal;

import static Utils.UserInputUtils.capitalizeWords;

public class Drink implements MenuItem, Printable {
    DrinkSize drinkSize;
    DrinkFlavor drinkFlavor;

    public Drink(DrinkSize drinkSize, DrinkFlavor drinkFlavor) {
        this.drinkSize = drinkSize;
        this.drinkFlavor = drinkFlavor;
    }

    @Override
    public BigDecimal getPrice() {
        return switch (drinkSize) {
            case SMALL -> BigDecimal.valueOf(2.00);
            case MEDIUM -> BigDecimal.valueOf(2.50);
            case LARGE -> BigDecimal.valueOf(3.00);
        };
    }

    @Override
    public void printSummary(PrintStream print) {
        String flavor = capitalizeWords(drinkFlavor.toString().replace("_", " ").toLowerCase());
        String size = capitalizeWords(drinkSize.toString().replace("_", " ").toLowerCase());
        print.printf("Drink: %s (%s) - $%.2f%n", flavor, size, getPrice());
    }
}
