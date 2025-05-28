package builders;

import models.Drink;
import models.enums.DrinkFlavor;
import models.enums.DrinkSize;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DrinkBuilderTest {

    private InputStream originalIn;

    @BeforeEach
    void setUp() {
        originalIn = System.in;
    }

    @AfterEach
    void tearDown() {
        System.setIn(originalIn);
    }

    @Test
    void build_returnsCorrectDrinkBasedOnUserInput() {
        String simulatedInput = String.join("\n", List.of("0", "0"));
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        DrinkBuilder builder = new DrinkBuilder();
        Drink drink = builder.build();

        assertNotNull(drink);
        assertEquals(DrinkSize.SMALL, drink.getDrinkSize());
        assertEquals(DrinkFlavor.COCA_COLA, drink.getDrinkFlavor());
    }
}
