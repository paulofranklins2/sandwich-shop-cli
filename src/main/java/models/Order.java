package models;

import interfaces.MenuItem;
import interfaces.Printable;
import lombok.Getter;

import java.io.PrintStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a customer's order containing a list of {@link MenuItem} objects
 * such as sandwiches, drinks, and chips. Supports pricing and summary printing.
 */
public class Order implements Printable, MenuItem {

    @Getter
    private final List<MenuItem> items;

    /**
     * Constructs an empty order.
     */
    public Order() {
        items = new ArrayList<>();
    }

    /**
     * Adds a {@link MenuItem} to the order.
     *
     * @param item the item to add
     */
    public void addItem(MenuItem item) {
        items.add(item);
    }

    /**
     * Clears all items from the order.
     */
    public void clear() {
        items.clear();
    }

    /**
     * Checks whether the order contains any items.
     *
     * @return true if the order is empty; false otherwise
     */
    public boolean isEmpty() {
        return items.isEmpty();
    }

    /**
     * Calculates and returns the total price of all items in the order.
     *
     * @return the total price as a {@link BigDecimal}
     */
    @Override
    public BigDecimal getPrice() {
        return items.stream()
                .map(MenuItem::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    /**
     * Prints a formatted summary of the order, including each item's details
     * and the total cost.
     *
     * @param out the output stream to print to
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
