package data;

import models.SignatureSandwich;
import models.enums.BreadType;
import models.enums.SandwichSize;
import models.enums.Topping;

import java.util.List;

/**
 * Provides a static list of predefined {@link SignatureSandwich} presets
 * with curated ingredients, bread types, and sizes.
 */
public class SignatureSandwiches {

    /**
     * Returns a list of default {@link SignatureSandwich} options.
     * Each sandwich includes a name, size, bread type, toppings, and toasting preference.
     *
     * @return list of SignatureSandwich instances
     */
    public static List<SignatureSandwich> getAll() {
        return List.of(
                new SignatureSandwich("BLT", SandwichSize.EIGHT_INCH, BreadType.WHITE,
                        List.of(Topping.BACON, Topping.LETTUCE, Topping.TOMATOES),
                        List.of(), true),

                new SignatureSandwich("Philly Cheesesteak", SandwichSize.TWELVE_INCH, BreadType.ITALIAN,
                        List.of(Topping.STEAK, Topping.PEPPERS, Topping.ONIONS, Topping.AMERICAN),
                        List.of(Topping.AMERICAN), true),

                new SignatureSandwich("Turkey Club", SandwichSize.TWELVE_INCH, BreadType.WHEAT,
                        List.of(Topping.CHICKEN, Topping.BACON, Topping.LETTUCE, Topping.TOMATOES, Topping.SWISS),
                        List.of(), false),

                new SignatureSandwich("Veggie Delight", SandwichSize.EIGHT_INCH, BreadType.WRAP,
                        List.of(Topping.LETTUCE, Topping.TOMATOES, Topping.CUCUMBERS, Topping.PEPPERS, Topping.MUSHROOMS),
                        List.of(), false),

                new SignatureSandwich("Spicy Italian", SandwichSize.EIGHT_INCH, BreadType.ITALIAN,
                        List.of(Topping.SALAMI, Topping.PEPPERS, Topping.JALAPENOS, Topping.PROVOLONE),
                        List.of(), true),

                new SignatureSandwich("Classic Ham & Cheese", SandwichSize.FOUR_INCH, BreadType.WHITE,
                        List.of(Topping.HAM, Topping.AMERICAN),
                        List.of(), false)
        );
    }
}
