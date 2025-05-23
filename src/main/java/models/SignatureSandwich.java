package models;

import lombok.Getter;
import models.enums.BreadType;
import models.enums.SandwichSize;
import models.enums.Topping;

import java.util.List;

/**
 * Represents a pre-defined sandwich with a name and default ingredients.
 * Inherits functionality from the base {@link Sandwich} class.
 */
@Getter
public class SignatureSandwich extends Sandwich {

    private final String name;

    /**
     * Constructs a SignatureSandwich with a name and specific sandwich configuration.
     *
     * @param name         the name of the signature sandwich (e.g., "BLT", "Philly Cheesesteak")
     * @param size         the {@link SandwichSize} of the sandwich
     * @param bread        the {@link BreadType} used
     * @param toppings     the default toppings included
     * @param extras       extra toppings to be included
     * @param isToasted    whether the sandwich is toasted
     */
    public SignatureSandwich(String name, SandwichSize size, BreadType bread,
                             List<Topping> toppings, List<Topping> extras, boolean isToasted) {
        super(size, bread, toppings, extras, isToasted);
        this.name = name;
    }

    /**
     * Prints a summary prefixed with the sandwich name followed by the detailed configuration.
     *
     * @param out the output stream to print to
     */
    @Override
    public void printSummary(java.io.PrintStream out) {
        out.println("Signature Sandwich: " + name);
        super.printSummary(out);
    }
}
