package data;

import models.SignatureSandwich;
import models.enums.BreadType;
import models.enums.SandwichSize;
import models.enums.Topping;

import java.util.List;

/**
 * Holds a static list of ready-to-go {@link SignatureSandwich} presets
 * with handpicked ingredients, bread, and sizes.
 */
public class SignatureSandwiches {

    /**
     * Gives back a list of default {@link SignatureSandwich}es.
     * Each one comes with a name, size, bread, toppings, and toast setting.
     *
     * @return list of SignatureSandwiches
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
                        List.of(), false),
                new SignatureSandwich("Just Vibes", SandwichSize.FOUR_INCH, BreadType.WHITE,
                        List.of(), List.of(), false), // no toppings, no toast, just empty carbs

                new SignatureSandwich("Sauce Boss", SandwichSize.TWELVE_INCH, BreadType.ITALIAN,
                        List.of(), List.of(Topping.MAYO, Topping.MUSTARD, Topping.HOT_SAUCE, Topping.BBQ, Topping.RANCH), false),

                new SignatureSandwich("Meat Tornado", SandwichSize.TWELVE_INCH, BreadType.WHEAT,
                        List.of(Topping.STEAK, Topping.HAM, Topping.BACON, Topping.SALAMI, Topping.CHICKEN), List.of(), true),

                new SignatureSandwich("Health Crisis", SandwichSize.TWELVE_INCH, BreadType.ITALIAN,
                        List.of(Topping.BACON, Topping.HAM, Topping.SWISS, Topping.AMERICAN), List.of(Topping.MAYO, Topping.RANCH), true),

                new SignatureSandwich("Crunch Wrap Rejected", SandwichSize.EIGHT_INCH, BreadType.WRAP,
                        List.of(Topping.LETTUCE, Topping.CUCUMBERS, Topping.TOMATOES), List.of(Topping.HOT_SAUCE), false),

                new SignatureSandwich("Toasty Ghosty", SandwichSize.FOUR_INCH, BreadType.WHITE,
                        List.of(), List.of(), true), // nothing inside, but toasted – suspiciously warm

                new SignatureSandwich("College Special", SandwichSize.EIGHT_INCH, BreadType.WHITE,
                        List.of(Topping.AMERICAN), List.of(Topping.MUSTARD), false), // it's what you find in the mini fridge

                new SignatureSandwich("Overcompensator", SandwichSize.TWELVE_INCH, BreadType.ITALIAN,
                        List.of(Topping.STEAK, Topping.CHICKEN, Topping.BACON, Topping.ONIONS, Topping.JALAPENOS, Topping.SWISS, Topping.AMERICAN),
                        List.of(Topping.BBQ, Topping.RANCH, Topping.MUSTARD), true)
        );
    }
}
