package models;

import interfaces.MenuItem;
import models.enums.DrinkFlavor;
import models.enums.DrinkSize;

import java.math.BigDecimal;

public class Drink implements MenuItem {
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
    public String toString() {
        return "Drink Size: " + drinkSize.toString() + "\nDrink Flavor: " + drinkFlavor.name().replace("_", " ");
    }
}
