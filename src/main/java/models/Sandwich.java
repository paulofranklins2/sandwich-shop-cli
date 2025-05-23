package models;

import interfaces.MenuItem;
import interfaces.Printable;
import models.enums.BreadType;
import models.enums.SandwichSize;
import models.enums.Topping;
import models.enums.ToppingType;

import java.io.PrintStream;
import java.math.BigDecimal;
import java.util.List;

import static utils.UserInputUtils.formatEnum;
import static utils.UserInputUtils.formatEnumList;

public record Sandwich(SandwichSize sandwichSize, BreadType breadType, List<Topping> toppings,
                       List<Topping> extraToppings, Boolean isToasted) implements MenuItem, Printable {

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

    @Override
    public void printSummary(PrintStream out) {
        out.println("Sandwich:");
        out.println("- Size: " + sandwichSize); // already formatted in enum
        out.println("- Bread: " + formatEnum(breadType));
        out.println("- Toasted: " + (isToasted ? "Yes" : "No"));
        out.println("- Toppings: " + formatEnumList(toppings));
        out.println("- Extra Toppings: " + formatEnumList(extraToppings));
        out.printf("- Price: $%.2f%n", getPrice());
    }

    // Meat Pricing
    private BigDecimal getMeatPrice(SandwichSize size) {
        return switch (size) {
            case FOUR_INCH -> BigDecimal.valueOf(1.00);
            case EIGHT_INCH -> BigDecimal.valueOf(2.00);
            case TWELVE_INCH -> BigDecimal.valueOf(3.00);
        };
    }

    private BigDecimal getExtraMeatPrice(SandwichSize size) {
        return switch (size) {
            case FOUR_INCH -> BigDecimal.valueOf(0.50);
            case EIGHT_INCH -> BigDecimal.valueOf(1.00);
            case TWELVE_INCH -> BigDecimal.valueOf(1.50);
        };
    }

    // Cheese Pricing
    private BigDecimal getCheesePrice(SandwichSize size) {
        return switch (size) {
            case FOUR_INCH -> BigDecimal.valueOf(0.75);
            case EIGHT_INCH -> BigDecimal.valueOf(1.50);
            case TWELVE_INCH -> BigDecimal.valueOf(2.25);
        };
    }

    private BigDecimal getExtraCheesePrice(SandwichSize size) {
        return switch (size) {
            case FOUR_INCH -> BigDecimal.valueOf(0.30);
            case EIGHT_INCH -> BigDecimal.valueOf(0.60);
            case TWELVE_INCH -> BigDecimal.valueOf(0.90);
        };
    }
}
