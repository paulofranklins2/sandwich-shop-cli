package models.enums;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents all available toppings that can be added to a {@link models.Sandwich}.
 * Each topping is associated with a {@link ToppingType} category, such as MEAT, CHEESE, REGULAR, or SAUCE.
 */
@Getter
public enum Topping {

    // Meat Toppings
    STEAK(ToppingType.MEAT),
    HAM(ToppingType.MEAT),
    SALAMI(ToppingType.MEAT),
    ROAST_BEEF(ToppingType.MEAT),
    CHICKEN(ToppingType.MEAT),
    BACON(ToppingType.MEAT),

    // Cheese Toppings
    AMERICAN(ToppingType.CHEESE),
    PROVOLONE(ToppingType.CHEESE),
    CHEDDAR(ToppingType.CHEESE),
    SWISS(ToppingType.CHEESE),

    // Regular Toppings
    LETTUCE(ToppingType.REGULAR),
    PEPPERS(ToppingType.REGULAR),
    ONIONS(ToppingType.REGULAR),
    TOMATOES(ToppingType.REGULAR),
    JALAPENOS(ToppingType.REGULAR),
    CUCUMBERS(ToppingType.REGULAR),
    PICKLES(ToppingType.REGULAR),
    GUACAMOLE(ToppingType.REGULAR),
    MUSHROOMS(ToppingType.REGULAR),

    // Sauce Toppings
    MAYO(ToppingType.SAUCE),
    MUSTARD(ToppingType.SAUCE),
    KETCHUP(ToppingType.SAUCE),
    RANCH(ToppingType.SAUCE),
    THOUSAND_ISLANDS(ToppingType.SAUCE),
    VINAIGRETTE(ToppingType.SAUCE);

    private final ToppingType type;

    Topping(ToppingType type) {
        this.type = type;
    }

    /**
     * Checks if this topping belongs to the specified {@link ToppingType}.
     *
     * @param queryType the topping type to check against
     * @return true if this topping matches the provided type; false otherwise
     */
    public boolean isType(ToppingType queryType) {
        return this.type == queryType;
    }

    /**
     * Retrieves all toppings that belong to a given {@link ToppingType}.
     *
     * @param toppingType the category to filter by
     * @return a list of toppings of the specified type
     */
    public static List<Topping> getByType(ToppingType toppingType) {
        return Arrays.stream(Topping.values())
                .filter(t -> t.type == toppingType)
                .collect(Collectors.toList());
    }
}
