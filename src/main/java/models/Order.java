package models;

import interfaces.MenuItem;
import interfaces.Printable;
import lombok.Getter;

import java.io.PrintStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * A customer's order made up of {@link MenuItem}s like sandwiches, drinks, and chips.
 * You can get the total price and print out a summary.
 */
public class Order implements Printable, MenuItem {

    @Getter
    private final List<MenuItem> items;

    /**
     * Starts a new, empty order.
     */
    public Order() {
        items = new ArrayList<>();
    }

    /**
     * Adds an item to the order.
     *
     * @param item the thing you want to add
     */
    public void addItem(MenuItem item) {
        items.add(item);
    }

    /**
     * Wipes the order clean — removes everything.
     */
    public void clear() {
        items.clear();
    }

    /**
     * Checks if the order has anything in it.
     *
     * @return true if it’s empty, false if there’s stuff in it
     */
    public boolean isEmpty() {
        return items.isEmpty();
    }

    /**
     * Adds up the price of everything in the order.
     *
     * @return total cost as a {@link BigDecimal}
     */
    @Override
    public BigDecimal getPrice() {
        return items.stream()
                .map(MenuItem::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    /**
     * Prints out all the items in the order and shows the total.
     *
     * @param out where to print the summary
     */
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
