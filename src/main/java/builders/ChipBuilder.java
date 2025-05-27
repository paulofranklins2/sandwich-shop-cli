package builders;

import models.Chip;
import models.enums.ChipFlavor;

import static utils.UserPromptUtils.promptOption;

/**
 * Builds a {@link Chip} by asking the user what they want.
 */

public class ChipBuilder {

    /**
     * Asks the user to pick a chip flavor and makes a new {@link Chip} with it.
     *
     * @return a Chip based on what the user picked.
     */
    public Chip build() {
        ChipFlavor flavor = promptOption("Select Chip Flavor:", ChipFlavor.values());
        return new Chip(flavor);
    }
}
