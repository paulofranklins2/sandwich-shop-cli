package builders;

import models.Sandwich;
import models.enums.BreadType;
import models.enums.SandwichSize;
import models.enums.Topping;
import utils.ToppingEditor;

import java.util.ArrayList;
import java.util.List;

import static utils.UserPromptUtils.*;

/**
 * Builds custom {@link Sandwich}es by asking the user to pick a {@link BreadType},
 * {@link SandwichSize}, and toppings/extras.
 */
public class SandwichBuilder {

    /**
     * Builds and returns a custom {@link Sandwich} based on what the user picks—
     * bread, size, toppings, extras, and whether it's toasted.
     *
     * @return a Sandwich made from user choices in the CLI
     */
    public Sandwich build() {
//        printHeader("Custom Sandwich");

        BreadType breadType = promptOption("Choose bread", BreadType.values());
        SandwichSize sandwichSize = promptOption("Choose size", SandwichSize.values());

        List<Topping> toppings = new ArrayList<>();
        List<Topping> extras = new ArrayList<>();

        ToppingEditor.addToppings(toppings, extras);

        ToppingEditor.removeToppings(toppings);
        boolean isToasted = intPrompt("Toast sandwich? (1 = yes, 0 = no): ") == 1;

        return new Sandwich(sandwichSize, breadType, toppings, extras, isToasted);
    }
}
