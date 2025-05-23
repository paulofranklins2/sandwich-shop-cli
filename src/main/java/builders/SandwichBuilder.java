package builders;

import models.Sandwich;
import models.enums.BreadType;
import models.enums.SandwichSize;
import models.enums.Topping;
import utils.ToppingEditor;

import java.util.ArrayList;
import java.util.List;

import static utils.ConsolePrinter.printHeader;
import static utils.UserInputUtils.intPrompt;
import static utils.UserInputUtils.promptOption;

public class SandwichBuilder {

    public Sandwich build() {
        printHeader("Custom Sandwich");

        BreadType breadType = promptOption("Choose bread:", BreadType.values());
        SandwichSize sandwichSize = promptOption("Choose size:", SandwichSize.values());

        List<Topping> toppings = new ArrayList<>();
        List<Topping> extras = new ArrayList<>();

        ToppingEditor.addToppings(toppings, extras);

        boolean isToasted = intPrompt("Toast sandwich? (1 = yes, 0 = no): ") == 1;

        return new Sandwich(sandwichSize, breadType, toppings, extras, isToasted);
    }
}
