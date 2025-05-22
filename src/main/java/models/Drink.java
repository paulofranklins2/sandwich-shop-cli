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
        return null;
    }
}
