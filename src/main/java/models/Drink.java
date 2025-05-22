package models;

import interfaces.MenuItem;
import models.enums.DrinkSize;

import java.math.BigDecimal;

public class Drink implements MenuItem {
    DrinkSize drinkSize;
    String drinkFlavor;

    public Drink(DrinkSize drinkSize, String drinkFlavor) {
        this.drinkSize = drinkSize;
        this.drinkFlavor = drinkFlavor;
    }

    @Override
    public BigDecimal getPrice() {
        return null;
    }
}
