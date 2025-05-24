package builders;

import models.Chip;
import models.enums.ChipFlavor;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;

import static org.junit.jupiter.api.Assertions.*;

class ChipBuilderTest {

    @Test
    void build_returnsCorrectChipBasedOnUserInput() {
        // Simulate user entering "2" (index of FANTA for example)
        String simulatedInput = "0\n"; // Choose first option (LAYS_CLASSIC)
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        ChipBuilder builder = new ChipBuilder();
        Chip chip = builder.build();

        assertNotNull(chip);
        assertEquals(ChipFlavor.LAYS_CLASSIC, chip.getChipFlavor());
    }
}
