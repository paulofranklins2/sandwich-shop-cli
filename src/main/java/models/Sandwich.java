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

import static utils.UserInputUtils.formatEnum;
import static utils.UserInputUtils.formatEnumList;

/**
 * Represents a customizable sandwich that can be included in an order.
 * A sandwich has a size, bread type, a list of toppings and extra toppings,
 * and an option for being toasted.
 */
@Getter
public class Sandwich implements MenuItem, Printable {

    private final SandwichSize sandwichSize;
    private final BreadType breadType;
    private final List<Topping> toppings;
    private final List<Topping> extraToppings;
    private final Boolean isToasted;

    /**
     * Constructs a new {@code Sandwich} with the given configuration.
     *
     * @param sandwichSize   the size of the sandwich
     * @param breadType      the bread type used
     * @param toppings       regular toppings
     * @param extraToppings  additional toppings
     * @param isToasted      whether the sandwich is toasted
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
     * Calculates the price of the sandwich based on size and toppings.
     * Includes base price and surcharges for meats and cheeses.
     *
     * @return the total price of the sandwich
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
     * Prints a formatted summary of the sandwich's properties and total price.
     *
     * @param out the output stream to print to
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
     * Returns the cost of one meat topping for the given sandwich size.
     */
    protected BigDecimal getMeatPrice(SandwichSize size) {
        return switch (size) {
            case FOUR_INCH -> BigDecimal.valueOf(1.00);
            case EIGHT_INCH -> BigDecimal.valueOf(2.00);
            case TWELVE_INCH -> BigDecimal.valueOf(3.00);
        };
    }

    /**
     * Returns the cost of one extra meat topping for the given sandwich size.
     */
    protected BigDecimal getExtraMeatPrice(SandwichSize size) {
        return switch (size) {
            case FOUR_INCH -> BigDecimal.valueOf(0.50);
            case EIGHT_INCH -> BigDecimal.valueOf(1.00);
            case TWELVE_INCH -> BigDecimal.valueOf(1.50);
        };
    }

    /**
     * Returns the cost of one cheese topping for the given sandwich size.
     */
    protected BigDecimal getCheesePrice(SandwichSize size) {
        return switch (size) {
            case FOUR_INCH -> BigDecimal.valueOf(0.75);
            case EIGHT_INCH -> BigDecimal.valueOf(1.50);
            case TWELVE_INCH -> BigDecimal.valueOf(2.25);
        };
    }

    /**
     * Returns the cost of one extra cheese topping for the given sandwich size.
     */
    protected BigDecimal getExtraCheesePrice(SandwichSize size) {
        return switch (size) {
            case FOUR_INCH -> BigDecimal.valueOf(0.30);
            case EIGHT_INCH -> BigDecimal.valueOf(0.60);
            case TWELVE_INCH -> BigDecimal.valueOf(0.90);
        };
    }
}
