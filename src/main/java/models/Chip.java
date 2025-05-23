package models;

import interfaces.MenuItem;
import interfaces.Printable;
import lombok.Getter;
import models.enums.ChipFlavor;

import java.io.PrintStream;
import java.math.BigDecimal;

import static utils.UserInputUtils.capitalizeWords;

public class Chip implements MenuItem, Printable {

    @Getter
    private ChipFlavor chipFlavor;

    public Chip(ChipFlavor flavor) {
        this.chipFlavor = flavor;
    }

    @Override
    public BigDecimal getPrice() {
        return new BigDecimal("1.50");
    }

    @Override
    public void printSummary(PrintStream out) {
        String flavor = capitalizeWords(chipFlavor.toString().replace("_", " ").toLowerCase());
        out.printf("Chips: %s - $%.2f%n", flavor, getPrice());
    }
}
