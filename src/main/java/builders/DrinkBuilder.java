package builders;

import models.Drink;
import models.enums.DrinkFlavor;
import models.enums.DrinkSize;

import static utils.UserInputUtils.promptOption;

/**
 * Builder class responsible for creating {@link Drink} objects
 * by prompting the user to select a drink size and flavor.
 */
public class DrinkBuilder {

    /**
     * Prompts the user to choose a {@link DrinkSize} and {@link DrinkFlavor},
     * then constructs and returns a {@link Drink} object.
     *
     * @return a Drink instance based on user input
     */
    public Drink build() {
        DrinkSize size = promptOption("Select Drink Size:", DrinkSize.values());
        DrinkFlavor flavor = promptOption("Select Drink Flavor:", DrinkFlavor.values());
        return new Drink(size, flavor);
    }
}
