package builders;

import models.Chip;
import models.enums.ChipFlavor;

import static utils.UserInputUtils.promptOption;

/**
 * Builder class responsible for constructing {@link Chip} objects
 * through user interaction.
 */
public class ChipBuilder {

    /**
     * Prompts the user to select a chip flavor and returns a new {@link Chip} instance.
     *
     * @return a Chip object based on the user's selected flavor.
     */
    public Chip build() {
        ChipFlavor flavor = promptOption("Select Chip Flavor:", ChipFlavor.values());
        return new Chip(flavor);
    }
}
