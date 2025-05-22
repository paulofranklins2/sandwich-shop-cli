package models;

import interfaces.MenuItem;
import interfaces.Printable;
import models.enums.BreadType;
import models.enums.SandwichSize;
import models.enums.Topping;

import java.math.BigDecimal;
import java.util.List;

public class Sandwich implements MenuItem, Printable {
    private SandwichSize sandwichSize;
    private BreadType breadType;
    private List<Topping> toppings;
    private List<Topping> extraToppings;
    private Boolean isToasted;

    @Override
    public BigDecimal getPrice() {
        return null;
    }

    @Override
    public void printSummary() {

    }
}
