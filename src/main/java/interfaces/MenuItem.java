package interfaces;

import java.math.BigDecimal;

/**
 * Represents an item that has a price and can be included in an order.
 * Implementing classes must define how the price is calculated.
 */
@FunctionalInterface
public interface MenuItem {

    /**
     * Returns the price of the item.
     *
     * @return the item's cost as a {@link BigDecimal}
     */
    BigDecimal getPrice();
}
