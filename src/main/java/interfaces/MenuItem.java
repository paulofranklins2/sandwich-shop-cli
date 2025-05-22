package interfaces;

import java.math.BigDecimal;

@FunctionalInterface
public interface MenuItem {
    BigDecimal getPrice();
}
