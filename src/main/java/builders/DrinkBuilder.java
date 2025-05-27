package builders;

import models.Drink;
import models.enums.DrinkFlavor;
import models.enums.DrinkSize;

import static utils.UserPromptUtils.promptOption;

/**
 * Builds a {@link Drink} by asking the user to pick a size and flavor.
 */
public class DrinkBuilder {

    /**
     * Asks the user to pick a {@link DrinkSize} and {@link DrinkFlavor},
     * then puts together and returns a {@link Drink}.
     *
     * @return a Drink based on what the user chose
     */
    public Drink build() {
        DrinkSize size = promptOption("Select Drink Size:", DrinkSize.values());
        DrinkFlavor flavor = promptOption("Select Drink Flavor:", DrinkFlavor.values());
        return new Drink(size, flavor);
    }
}
