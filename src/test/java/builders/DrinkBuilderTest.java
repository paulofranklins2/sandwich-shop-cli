package builders;

import models.Drink;
import models.enums.DrinkFlavor;
import models.enums.DrinkSize;
import org.junit.jupiter.api.Test;
import utils.UserPromptUtils;

import java.io.ByteArrayInputStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class DrinkBuilderTest {

    @Test
    void build_returnsCorrectDrinkBasedOnUserInput() {
        String simulatedInput = String.join("\n", List.of("0", "0"));
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        UserPromptUtils.resetScanner();

        DrinkBuilder builder = new DrinkBuilder();
        Drink drink = builder.build();

        assertNotNull(drink);
        assertEquals(DrinkSize.SMALL, drink.getDrinkSize());
        assertEquals(DrinkFlavor.COCA_COLA, drink.getDrinkFlavor());
    }
}
