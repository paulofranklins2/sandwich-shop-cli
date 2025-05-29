package models;

import interfaces.MenuItem;
import interfaces.Printable;
import lombok.Getter;
import models.enums.BreadType;
import models.enums.SandwichSize;
import models.enums.Topping;
import models.enums.ToppingType;

import java.io.PrintStream;
import java.math.BigDecimal;
import java.util.List;

import static utils.ConsolePrinter.formatEnum;
import static utils.ConsolePrinter.formatEnumList;

/**
 * A customizable sandwich you can add to an order.
 * Has a size, bread, toppings, extras, and you can toast it too.
 */
@Getter
public class Sandwich implements MenuItem, Printable {

    private final SandwichSize sandwichSize;
    private final BreadType breadType;
    private final List<Topping> toppings;
    private final List<Topping> extraToppings;
    private final Boolean isToasted;

    /**
     * Builds a new sandwich with all the stuff the user picked.
     *
     * @param sandwichSize   how big the sandwich is
     * @param breadType      what kind of bread it's on
     * @param toppings       regular toppings
     * @param extraToppings  extras the user added
     * @param isToasted      true if it’s toasted
     */
    public Sandwich(SandwichSize sandwichSize, BreadType breadType, List<Topping> toppings,
                    List<Topping> extraToppings, Boolean isToasted) {
        this.sandwichSize = sandwichSize;
        this.breadType = breadType;
        this.toppings = toppings;
        this.extraToppings = extraToppings;
        this.isToasted = isToasted;
    }

    /**
     * Figures out how much the sandwich costs based on size and toppings.
     * Adds extra charges for meats and cheeses.
     *
     * @return total sandwich price
     */
    @Override
    public BigDecimal getPrice() {
        BigDecimal finalPrice = switch (sandwichSize) {
            case FOUR_INCH -> BigDecimal.valueOf(5.50);
            case EIGHT_INCH -> BigDecimal.valueOf(7.00);
            case TWELVE_INCH -> BigDecimal.valueOf(8.50);
        };

        for (Topping topping : toppings) {
            if (topping.isType(ToppingType.MEAT)) {
                finalPrice = finalPrice.add(getMeatPrice(sandwichSize));
            } else if (topping.isType(ToppingType.CHEESE)) {
                finalPrice = finalPrice.add(getCheesePrice(sandwichSize));
            }
        }

        for (Topping topping : extraToppings) {
            if (topping.isType(ToppingType.MEAT)) {
                finalPrice = finalPrice.add(getExtraMeatPrice(sandwichSize));
            } else if (topping.isType(ToppingType.CHEESE)) {
                finalPrice = finalPrice.add(getExtraCheesePrice(sandwichSize));
            }
        }

        return finalPrice;
    }

    /**
     * Prints a summary of the sandwich — size, bread, toppings, and price.
     *
     * @param out where to print it
     */
    @Override
    public void printSummary(PrintStream out) {
        out.println("Sandwich:");
        out.println("- Size: " + sandwichSize);
        out.println("- Bread: " + formatEnum(breadType));
        out.println("- Toasted: " + (isToasted ? "Yes" : "No"));
        out.println("- Toppings: " + formatEnumList(toppings));
        out.println("- Extra Toppings: " + formatEnumList(extraToppings));
        out.printf("- Price: $%.2f%n", getPrice());
    }

    /**
     * Price for a regular meat topping based on sandwich size.
     */
    protected BigDecimal getMeatPrice(SandwichSize size) {
        return switch (size) {
            case FOUR_INCH -> BigDecimal.valueOf(1.00);
            case EIGHT_INCH -> BigDecimal.valueOf(2.00);
            case TWELVE_INCH -> BigDecimal.valueOf(3.00);
        };
    }

    /**
     * Price for an extra meat topping based on sandwich size.
     */
    protected BigDecimal getExtraMeatPrice(SandwichSize size) {
        return switch (size) {
            case FOUR_INCH -> BigDecimal.valueOf(0.50);
            case EIGHT_INCH -> BigDecimal.valueOf(1.00);
            case TWELVE_INCH -> BigDecimal.valueOf(1.50);
        };
    }

    /**
     * Price for a regular cheese topping based on sandwich size.
     */
    protected BigDecimal getCheesePrice(SandwichSize size) {
        return switch (size) {
            case FOUR_INCH -> BigDecimal.valueOf(0.75);
            case EIGHT_INCH -> BigDecimal.valueOf(1.50);
            case TWELVE_INCH -> BigDecimal.valueOf(2.25);
        };
    }

    /**
     * Price for an extra cheese topping based on sandwich size.
     */
    protected BigDecimal getExtraCheesePrice(SandwichSize size) {
        return switch (size) {
            case FOUR_INCH -> BigDecimal.valueOf(0.30);
            case EIGHT_INCH -> BigDecimal.valueOf(0.60);
            case TWELVE_INCH -> BigDecimal.valueOf(0.90);
        };
    }
}

