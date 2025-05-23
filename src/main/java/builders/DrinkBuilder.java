package builders;

import models.Drink;
import models.enums.DrinkFlavor;
import models.enums.DrinkSize;

import static utils.UserInputUtils.promptOption;

public class DrinkBuilder {
    public Drink build() {
        DrinkSize size = promptOption("Select Drink Size:", DrinkSize.values());
        DrinkFlavor flavor = promptOption("Select Drink Flavor:", DrinkFlavor.values());
        return new Drink(size, flavor);
    }
}
