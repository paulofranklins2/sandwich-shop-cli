package builders;

import models.Chip;
import models.enums.ChipFlavor;
import org.junit.jupiter.api.Test;
import utils.UserPromptUtils;

import java.io.ByteArrayInputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ChipBuilderTest {

    @Test
    void build_returnsCorrectChipBasedOnUserInput() {
        String simulatedInput = "0";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        UserPromptUtils.resetScanner();

        ChipBuilder builder = new ChipBuilder();
        Chip chip = builder.build();

        assertNotNull(chip);
        assertEquals(ChipFlavor.LAYS_CLASSIC, chip.getChipFlavor());
    }
}
