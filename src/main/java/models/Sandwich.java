package models;

import interfaces.MenuItem;
import interfaces.Printable;
import lombok.Getter;
import models.enums.BreadType;
import models.enums.SandwichSize;
import models.enums.Topping;
import models.enums.ToppingType;

import java.math.BigDecimal;
import java.util.List;

@Getter
public class Sandwich implements MenuItem, Printable {
    private final SandwichSize sandwichSize;
    private final BreadType breadType;
    private final List<Topping> toppings;
    private final List<Topping> extraToppings;
    private final Boolean isToasted;

    //Starting Price 5.50 + 1.50 each size

    // Start 0.75 > extra 0.75 by size and 0.30 if extra
    //Cheese
    //- american
    //- provolone
    //- cheddar
    //- swiss

    // 1$ by size and 0.50$ extra by size
    //Meats
    //- steak
    //- ham
    //- salami
    //- roast beef
    //- chicken
    //- bacon

    // TODO: Refine pricing logic — current implementation is placeholder for testing purposes.
    // Needs adjustment to accurately calculate prices based on sandwich size and topping types.

    @Override
    public BigDecimal getPrice() {
        BigDecimal finalPrice = BigDecimal.valueOf(5.50);
        switch (sandwichSize) {
            case EIGHT_INCH -> finalPrice = finalPrice.add(new BigDecimal("1.50"));
            case TWELVE_INCH -> finalPrice = finalPrice.add(new BigDecimal("2.50"));
        }

        for (Topping topping : toppings) {
            if (topping.isType(ToppingType.MEAT)) {
                finalPrice = finalPrice.add(new BigDecimal("1.00"));
            }
            if (topping.isType(ToppingType.CHEESE))
                finalPrice = finalPrice.add(new BigDecimal("0.75"));
        }

        for (Topping topping : extraToppings) {
            if (topping.isType(ToppingType.MEAT)) {
                finalPrice = finalPrice.add(new BigDecimal("0.75"));
            }
            if (topping.isType(ToppingType.CHEESE))
                finalPrice = finalPrice.add(new BigDecimal("0.30"));
        }
        return finalPrice;
    }

    @Override
    public void printSummary() {
        System.out.println(
                "Sandwich Size: " + sandwichSize.toString() + "\nBread Type: " + breadType.toString()
                        + "\nRegular Toppings: " + toppings.toString() + "\nExtra Toppings: " + extraToppings.toString()
                        + "\nIs Toasted: " + isToasted
        );
    }

    Sandwich(SandwichSize sandwichSize, BreadType breadType, List<Topping> lassiToppings, List<Topping> extraToppings, Boolean isToasted) {
        this.sandwichSize = sandwichSize;
        this.breadType = breadType;
        this.toppings = lassiToppings;
        this.extraToppings = extraToppings;
        this.isToasted = isToasted;
    }

    @Override
    public String toString() {
        return "Sandwich Size: " + sandwichSize.toString() + "\nBread Type: " + breadType.toString()
                + "\nRegular Toppings: " + toppings.toString() + "\nExtra Toppings: " + extraToppings.toString()
                + "\nIs Toasted: " + isToasted;
    }
}
