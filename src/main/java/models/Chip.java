package models;

import interfaces.MenuItem;
import lombok.Getter;
import models.enums.ChipFlavor;

import java.math.BigDecimal;

public class Chip implements MenuItem {
    @Getter
    private ChipFlavor chipFlavor;

    public Chip(ChipFlavor flavor) {
        this.chipFlavor = flavor;
    }

    @Override
    public BigDecimal getPrice() {
        return new BigDecimal("1.50");
    }
}
