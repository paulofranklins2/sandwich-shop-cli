package models;

import interfaces.MenuItem;
import interfaces.Printable;

import java.math.BigDecimal;

public class Order implements Printable, MenuItem {

    public void addItem(MenuItem item) {
    }

    @Override
    public BigDecimal getPrice() {
        return null;
    }

    @Override
    public void printSummary() {
    }
}
