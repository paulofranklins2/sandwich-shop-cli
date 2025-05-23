package builders;

import models.Chip;
import models.enums.ChipFlavor;

import static utils.UserInputUtils.promptOption;

public class ChipBuilder {
    public Chip build() {
        ChipFlavor flavor = promptOption("Select Chip Flavor:", ChipFlavor.values());
        return new Chip(flavor);
    }
}
