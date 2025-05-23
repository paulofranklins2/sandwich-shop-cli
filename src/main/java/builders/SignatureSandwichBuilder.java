package builders;

import data.SignatureSandwiches;
import models.Sandwich;
import models.SignatureSandwich;
import models.enums.Topping;
import utils.ToppingEditor;

import java.util.ArrayList;
import java.util.List;

import static utils.ConsolePrinter.printHeader;
import static utils.ConsolePrinter.printLine;
import static utils.UserInputUtils.intPrompt;

/**
 * Builder class for constructing a {@link Sandwich} based on predefined {@link SignatureSandwich} presets.
 * Allows users to modify base toppings and extras during the build process.
 */
public class SignatureSandwichBuilder {

    /**
     * Displays a list of {@link SignatureSandwich} options, allows the user to select one,
     * and optionally modify the toppings and extras. Returns a new {@link Sandwich} instance.
     *
     * @return a customized Sandwich based on a signature preset
     */
    public Sandwich build() {
        List<SignatureSandwich> options = SignatureSandwiches.getAll();

        printHeader("Signature Sandwiches");
        for (int i = 0; i < options.size(); i++) {
            System.out.printf("[%d] - %s%n", i, options.get(i).getName());
        }

        int index = intPrompt("Choose: ");
        if (index < 0 || index >= options.size()) {
            printLine("Invalid selection.");
            return null;
        }

        SignatureSandwich base = options.get(index);

        List<Topping> toppings = new ArrayList<>(base.getToppings());
        List<Topping> extras = new ArrayList<>(base.getExtraToppings());

        ToppingEditor.removeToppings(toppings);
        ToppingEditor.addToppings(toppings, extras);

        return new Sandwich(base.getSandwichSize(), base.getBreadType(), toppings, extras, base.getIsToasted());
    }
}
