package interfaces;

import java.math.BigDecimal;

/**
 * Something you can add to an order that has a price.
 * Anything that uses this has to say how much it costs.
 */
@FunctionalInterface
public interface MenuItem {

    /**
     * Tells you how much the item costs.
     *
     * @return the price as a {@link BigDecimal}
     */
    BigDecimal getPrice();
}
