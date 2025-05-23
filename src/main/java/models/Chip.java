package models;

import interfaces.MenuItem;
import interfaces.Printable;
import lombok.Getter;
import models.enums.ChipFlavor;

import java.io.PrintStream;
import java.math.BigDecimal;

import static utils.UserInputUtils.capitalizeWords;

/**
 * Represents a bag of chips that can be added to an order.
 * Each chip has a {@link ChipFlavor} and a fixed price.
 */
public class Chip implements MenuItem, Printable {

    @Getter
    private final ChipFlavor chipFlavor;

    /**
     * Constructs a Chip with the given flavor.
     *
     * @param flavor the chip flavor selected
     */
    public Chip(ChipFlavor flavor) {
        this.chipFlavor = flavor;
    }

    /**
     * Returns the price of the chip item.
     *
     * @return the chip price as a {@link BigDecimal}
     */
    @Override
    public BigDecimal getPrice() {
        return new BigDecimal("1.50");
    }

    /**
     * Prints a formatted summary of the chip, including its flavor and price.
     *
     * @param out the output stream to print to
     */
    @Override
    public void printSummary(PrintStream out) {
        String flavor = capitalizeWords(chipFlavor.toString().replace("_", " ").toLowerCase());
        out.printf("Chips: %s - $%.2f%n", flavor, getPrice());
    }
}
