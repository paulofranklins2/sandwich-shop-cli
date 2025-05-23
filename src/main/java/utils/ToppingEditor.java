package utils;

import models.enums.Topping;
import models.enums.ToppingType;

import java.util.List;

import static utils.ConsolePrinter.printLine;
import static utils.UserInputUtils.*;

public class ToppingEditor {

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