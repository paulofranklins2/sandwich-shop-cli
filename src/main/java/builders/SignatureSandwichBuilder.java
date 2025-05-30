package builders;

import data.SignatureSandwiches;
import models.Sandwich;
import models.SignatureSandwich;
import models.enums.Topping;
import utils.ToppingEditor;

import java.util.ArrayList;
import java.util.List;

import static utils.ConsolePrinter.printHeader;
import static utils.UserPromptUtils.intPrompt;

/**
 * Builds a {@link Sandwich} using a preset {@link SignatureSandwich}.
 * Lets the user tweak the toppings and extras if they want.
 */
public class SignatureSandwichBuilder {

    /**
     * Shows the list of {@link SignatureSandwich} options, lets the user pick one,
     * and change the toppings/extras if they want. Returns the final {@link Sandwich}.
     *
     * @return a custom Sandwich based on a preset
     */
    public Sandwich build() {
        List<SignatureSandwich> options = SignatureSandwiches.getAll();

        printHeader("Signature Sandwiches");
        for (int i = 0; i < options.size(); i++) {
            System.out.printf("[%d] - %s%n", i, options.get(i).getName());
        }

        int index = intPrompt("Choose: ");
        if (index < 0 || index >= options.size()) {
            System.out.println("Invalid selection.");
            return null;
        }

        SignatureSandwich base = options.get(index);

        List<Topping> toppings = new ArrayList<>(base.getToppings());
        List<Topping> extras = new ArrayList<>(base.getExtraToppings());

        ToppingEditor.removeToppings(toppings);
        ToppingEditor.addToppings(toppings, extras);

        return new SignatureSandwich(base.getName(), base.getSandwichSize(), base.getBreadType(), toppings, extras, base.getIsToasted());
    }
}
