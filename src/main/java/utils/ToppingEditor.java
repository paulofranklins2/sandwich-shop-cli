package utils;

import models.enums.Topping;
import models.enums.ToppingType;

import java.util.List;

import static utils.ConsolePrinter.formatEnum;
import static utils.ConsolePrinter.formatEnumList;
import static utils.UserPromptUtils.intPrompt;
import static utils.UserPromptUtils.promptOption;

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
        // Nothing to do if the list is empty
        if (toppings.isEmpty()) return;

        // Show current toppings
        System.out.println("Current Toppings: " + formatEnumList(toppings));

        // Ask if the user wants to remove any
        if (intPrompt("Would you like to remove any toppings? (1 = yes, 0 = no): ") != 1) return;

        boolean removing = true;
        while (removing && !toppings.isEmpty()) {
            // Show the list with indexes
            for (int i = 0; i < toppings.size(); i++) {
                System.out.printf("[%d] - %s%n", i, formatEnum(toppings.get(i)));
            }

            // Let user pick one to remove
            int index = intPrompt("Choose topping to remove (or -1 to stop): ");
            if (index >= 0 && index < toppings.size()) {
                toppings.remove(index);
            } else {
                // Exit if the user is done or input is invalid
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
        // Ask if the user wants to add toppings
        boolean adding = intPrompt("Would you like to add any toppings? (1 = yes, 0 = no): ") == 1;

        while (adding) {
            // Pick a topping category
            ToppingType type = promptOption("Choose Topping Category:", ToppingType.values());

            // Get available toppings from that category
            List<Topping> options = Topping.getByType(type);

            // Show the options
            for (int i = 0; i < options.size(); i++) {
                System.out.printf("[%d] - %s%n", i, formatEnum(options.get(i)));
            }

            // Let user pick one
            int index = intPrompt("Choose topping to add: ");
            if (index >= 0 && index < options.size()) {
                Topping selected = options.get(index);
                toppings.add(selected);

                // Ask if it should be marked as extra
                if (intPrompt("Add extra? (1 = yes, 0 = no): ") == 1) {
                    extras.add(selected);
                }
            }

            // Ask if the user wants to keep adding
            adding = intPrompt("Add more toppings? (1 = yes, 0 = no): ") == 1;
        }
    }
}
