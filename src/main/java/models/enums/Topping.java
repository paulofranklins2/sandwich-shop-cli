package models.enums;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public enum Topping {
    /*
     * Meat Only
     */
    STEAK(ToppingType.MEAT),
    HAM(ToppingType.MEAT),
    SALAMI(ToppingType.MEAT),
    ROAST_BEEF(ToppingType.MEAT),
    CHICKEN(ToppingType.MEAT),
    BACON(ToppingType.MEAT),

    /*
     * Cheese Only
     */
    AMERICAN(ToppingType.CHEESE),
    PROVOLONE(ToppingType.CHEESE),
    CHEDDAR(ToppingType.CHEESE),
    SWISS(ToppingType.CHEESE),

    /*
     * Regular Only
     */
    LETTUCE(ToppingType.REGULAR),
    PEPPERS(ToppingType.REGULAR),
    ONIONS(ToppingType.REGULAR),
    TOMATOES(ToppingType.REGULAR),
    JALAPENOS(ToppingType.REGULAR),
    CUCUMBERS(ToppingType.REGULAR),
    PICKLES(ToppingType.REGULAR),
    GUACAMOLE(ToppingType.REGULAR),
    MUSHROOMS(ToppingType.REGULAR),

    /*
     * Sauce Only
     */
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

    public boolean isType(ToppingType queryType) {
        return this.type == queryType;
    }

    public static List<Topping> getByType(ToppingType toppingType) {
        return Arrays.stream(Topping.values())
                .filter(t -> t.type == toppingType)
                .collect(Collectors.toList());
    }
}
