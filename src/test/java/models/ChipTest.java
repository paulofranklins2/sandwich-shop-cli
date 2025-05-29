package models;

import models.enums.ChipFlavor;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ChipTest {

    @Test
    void getPrice_returnsCorrectPrice() {
        Chip chip = new Chip(ChipFlavor.LAYS_CLASSIC);
        assertEquals(new BigDecimal("1.50"), chip.getPrice(), "Chip price should be $1.50");
    }

    @Test
    void getChipFlavor_returnsCorrectFlavor() {
        Chip chip = new Chip(ChipFlavor.DORITOS_NACHO_CHEESE);
        assertEquals(ChipFlavor.DORITOS_NACHO_CHEESE, chip.getChipFlavor(), "Flavor should match input");
    }

    @Test
    void printSummary_outputsFormattedText() {
        Chip chip = new Chip(ChipFlavor.TAKIS_FUEGO);
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outContent);

        chip.printSummary(printStream);

        String output = outContent.toString().trim();
        assertTrue(output.contains("Takis Fuego"), "Output should contain formatted flavor name");
        assertTrue(output.contains("$1.50"), "Output should contain the price");
    }
}
