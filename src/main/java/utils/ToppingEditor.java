package utils;

import models.enums.Topping;
import models.enums.ToppingType;

import java.util.List;

import static utils.ConsolePrinter.printLine;
import static utils.UserInputUtils.*;

/**
 * Utility class for editing sandwich toppings through a CLI interface.
 * Allows users to remove or add toppings and designate extra toppings.
 */
public class ToppingEditor {

    /**
     * Allows the user to remove toppings from the given list interactively.
     *
     * @param toppings the list of current toppings
     */
    public static void removeToppings(List<Topping> toppings) {
        if (toppings.isEmpty()) return;

        printLine("Current Toppings: " + formatEnumList(toppings));
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
     * Allows the user to add toppings interactively.
     * Toppings can optionally be marked as "extra" and added to the extras list.
     *
     * @param toppings the list of base toppings
     * @param extras   the list of extra toppings
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
