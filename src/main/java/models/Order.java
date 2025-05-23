package models;

import interfaces.MenuItem;
import interfaces.Printable;
import lombok.Getter;

import java.io.PrintStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Order implements Printable, MenuItem {
    @Getter
    private final List<MenuItem> items;

    public Order() {
        items = new ArrayList<>();
    }

    public void addItem(MenuItem item) {
        items.add(item);
    }

    public void clear() {
        items.clear();
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    @Override
    public BigDecimal getPrice() {
        return items.stream()
                .map(MenuItem::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Override
    public void printSummary(PrintStream out) {
        out.println("=== Order Summary ===");
        if (items.isEmpty()) {
            out.println("No items in this order.");
        } else {
            for (MenuItem item : items) {
                if (item instanceof Printable printable) {
                    printable.printSummary(out);
                } else {
                    out.println("Unprintable item: " + item.getClass().getSimpleName());
                }
            }
        }
    }
}
