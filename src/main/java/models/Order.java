package models;

import interfaces.MenuItem;
import interfaces.Printable;
import models.enums.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Order implements Printable, MenuItem {
    List<MenuItem> items;

    public Order() {
        items = new ArrayList<>();
    }

    public void addItem(MenuItem item) {
        items.add(item);
    }

    @Override
    public BigDecimal getPrice() {
        BigDecimal finalPrice = BigDecimal.ZERO;
        for (MenuItem item : items) {
            finalPrice = finalPrice.add(item.getPrice());
        }
        return finalPrice;
    }

    @Override
    public void printSummary() {
        System.out.println("Order Summary");
        for (MenuItem item : items) {
            System.out.println(item);
        }
        System.out.println("Total: " + getPrice());
    }
}
