package models;

import interfaces.MenuItem;
import interfaces.Printable;
import lombok.Getter;
import models.enums.ChipFlavor;

import java.io.PrintStream;
import java.math.BigDecimal;

import static utils.UserPromptUtils.capitalizeWords;

/**
 * A bag of chips you can add to an order.
 * Has a {@link ChipFlavor} and a fixed price.
 */
public class Chip implements MenuItem, Printable {

    @Getter
    private final ChipFlavor chipFlavor;

    /**
     * Makes a Chip with the given flavor.
     *
     * @param flavor the chip flavor picked
     */
    public Chip(ChipFlavor flavor) {
        this.chipFlavor = flavor;
    }

    /**
     * Gets the price of the chips.
     *
     * @return the price as a {@link BigDecimal}
     */
    @Override
    public BigDecimal getPrice() {
        return new BigDecimal("1.50");
    }

    /**
     * Prints out the chip flavor and price in a nice format.
     *
     * @param out where to print the summary
     */
    @Override
    public void printSummary(PrintStream out) {
        String flavor = capitalizeWords(chipFlavor.toString().replace("_", " ").toLowerCase());
        out.printf("Chips: %s - $%.2f%n", flavor, getPrice());
    }
}
