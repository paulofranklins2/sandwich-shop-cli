package utils;

import models.enums.Topping;
import models.enums.ToppingType;

import java.util.List;

import static utils.UserPromptUtils.*;

/**
 * Helps the user tweak sandwich toppings in the CLI.
 * They can add, remove, or mark toppings as extras.
 */
public class ToppingEditor {

    /**
     * Lets the user remove toppings from a list one by one.
     *
     * @param toppings the current list of toppings
     */
    public static void removeToppings(List<Topping> toppings) {
        if (toppings.isEmpty()) return;

        System.out.println("Current Toppings: " + formatEnumList(toppings));
        if (intPrompt("Would you like to remove any toppings? (1 = yes, 0 = no): ") != 1) return;

        boolean removing = true;
        while (removing && !toppings.isEmpty()) {
            for (int i = 0; i < toppings.size(); i++) {
                System.out.printf("[%d] - %s%n", i, formatEnum(toppings.get(i)));
            }

            int index = intPrompt("Choose topping to remove (or -1 to stop): ");
            if (index >= 0 && index < toppings.size()) {
                toppings.remove(index);
            } else {
                removing = false;
            }
        }
    }

    /**
     * Lets the user add new toppings. They can also choose to make them "extras".
     *
     * @param toppings the base toppings list
     * @param extras   the list for any extra toppings
     */
    public static void addToppings(List<Topping> toppings, List<Topping> extras) {
        boolean adding = intPrompt("Would you like to add any toppings? (1 = yes, 0 = no): ") == 1;
        while (adding) {
            ToppingType type = promptOption("Choose Topping Category:", ToppingType.values());
            List<Topping> options = Topping.getByType(type);
            for (int i = 0; i < options.size(); i++) {
                System.out.printf("[%d] - %s%n", i, formatEnum(options.get(i)));
            }

            int index = intPrompt("Choose topping to add: ");
            if (index >= 0 && index < options.size()) {
                Topping selected = options.get(index);
                toppings.add(selected);
                if (intPrompt("Add extra? (1 = yes, 0 = no): ") == 1) {
                    extras.add(selected);
                }
            }

            adding = intPrompt("Add more toppings? (1 = yes, 0 = no): ") == 1;
        }
    }
}
