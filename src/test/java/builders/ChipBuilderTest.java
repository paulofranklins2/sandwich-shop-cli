package builders;

import models.Chip;
import models.enums.ChipFlavor;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

class ChipBuilderTest {

    private InputStream originalIn;

    @BeforeEach
    void setUp() {
        originalIn = System.in;
    }

    @AfterEach
    void tearDown() {
        System.setIn(originalIn);
    }

    @Test
    void build_returnsCorrectChipBasedOnUserInput() {
        String simulatedInput = "0";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        ChipBuilder builder = new ChipBuilder();
        Chip chip = builder.build();

        assertNotNull(chip);
        assertEquals(ChipFlavor.LAYS_CLASSIC, chip.getChipFlavor());
    }
}
