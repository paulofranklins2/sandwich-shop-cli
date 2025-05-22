package models.enums;

import interfaces.MenuItem;

import java.math.BigDecimal;

public class Chips implements MenuItem {
    private String type;

    public Chips(String type) {
        this.type = type;
    }

    @Override
    public BigDecimal getPrice() {
        return null;
    }
}
