package builders;

import models.Sandwich;
import models.enums.*;

import java.util.ArrayList;
import java.util.List;

import static utils.UserInputUtils.*;

public class SandwichBuilder {
    public Sandwich build() {
        BreadType breadType = promptOption("Select Bread Type:", BreadType.values());
        SandwichSize sandwichSize = promptOption("Select Sandwich Size:", SandwichSize.values());

        List<Topping> toppings = new ArrayList<>();
        List<Topping> extraToppings = new ArrayList<>();

        boolean addingToppings = true;
        while (addingToppings) {
            ToppingType toppingType = promptOption("Select Topping Category:", ToppingType.values());
            List<Topping> availableToppings = Topping.getByType(toppingType);
            printEnumOptions(availableToppings);
            int index = intPrompt("Choose topping: ");
            if (index >= 0 && index < availableToppings.size()) {
                Topping selected = availableToppings.get(index);
                toppings.add(selected);
                if (intPrompt("Add extra? (1 = yes, 0 = no): ") == 1) extraToppings.add(selected);
                addingToppings = intPrompt("Add more toppings? (1 = yes, 0 = no): ") == 1;
            }
        }

        boolean isToasted = intPrompt("Toast sandwich? (1 = yes, 0 = no): ") == 1;
        return new Sandwich(sandwichSize, breadType, toppings, extraToppings, isToasted);
    }

    private void printEnumOptions(List<? extends Enum<?>> list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.printf("[%d] - %s%n", i, capitalizeWords(list.get(i).toString().replace("_", " ").toLowerCase()));
        }
    }
}
