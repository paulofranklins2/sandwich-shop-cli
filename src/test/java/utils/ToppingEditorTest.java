package utils;

import models.enums.Topping;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ToppingEditorTest {

    @Test
    void removeToppings_shouldRemoveSelectedTopping() {
        String input = "1\n0\n-1\n"; // Yes to remove, remove index 0, then stop
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        UserPromptUtils.resetScanner(); // Reset scanner after setIn

        List<Topping> toppings = new ArrayList<>();
        toppings.add(Topping.LETTUCE);
        toppings.add(Topping.BACON);

        ToppingEditor.removeToppings(toppings);

        assertEquals(1, toppings.size());
        assertFalse(toppings.contains(Topping.LETTUCE));
    }

    @Test
    void removeToppings_shouldDoNothingIfUserSaysNo() {
        String input = "0\n"; // No to removing
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        UserPromptUtils.resetScanner();

        List<Topping> toppings = new ArrayList<>();
        toppings.add(Topping.LETTUCE);
        ToppingEditor.removeToppings(toppings);

        assertEquals(1, toppings.size());
    }

    @Test
    void addToppings_shouldAddToToppingsAndExtrasCorrectly() {
        String simulatedInput = String.join("\n", List.of(
                "1",  // Add toppings? Yes
                "2",  // What type? Regular (VEGGIES)
                "0",  // Adding Lettuce
                "1",  // Add extra? Yes
                "1",  // Add more? Yes
                "0",  // What type? Meat
                "0",  // Add Steak
                "0",  // Extra? No
                "0"   // Done
        ));
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        UserPromptUtils.resetScanner();

        List<Topping> toppings = new ArrayList<>();
        List<Topping> extras = new ArrayList<>();

        ToppingEditor.addToppings(toppings, extras);

        assertTrue(toppings.contains(Topping.LETTUCE));
        assertTrue(toppings.contains(Topping.STEAK));
        assertTrue(extras.contains(Topping.LETTUCE));
        assertFalse(extras.contains(Topping.STEAK));
    }

    @Test
    void addToppings_shouldNotAddAnythingIfUserSaysNoInitially() {
        String input = "0\n"; // No to adding
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        UserPromptUtils.resetScanner();

        List<Topping> toppings = new ArrayList<>();
        List<Topping> extras = new ArrayList<>();

        ToppingEditor.addToppings(toppings, extras);

        assertTrue(toppings.isEmpty());
        assertTrue(extras.isEmpty());
    }
}
