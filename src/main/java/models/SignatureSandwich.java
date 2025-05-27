package models;

import lombok.Getter;
import models.enums.BreadType;
import models.enums.SandwichSize;
import models.enums.Topping;

import java.util.List;

/**
 * A preset sandwich with a name and default ingredients.
 * Just a regular {@link Sandwich} with a personality.
 */
@Getter
public class SignatureSandwich extends Sandwich {

    private final String name;

    /**
     * Makes a SignatureSandwich with a name and a full setup.
     *
     * @param name      the sandwich’s name (like "BLT" or "Philly Cheesesteak")
     * @param size      the size of the sandwich
     * @param bread     the bread type
     * @param toppings  the regular toppings
     * @param extras    the extras
     * @param isToasted true if toasted
     */
    public SignatureSandwich(String name, SandwichSize size, BreadType bread,
                             List<Topping> toppings, List<Topping> extras, boolean isToasted) {
        super(size, bread, toppings, extras, isToasted);
        this.name = name;
    }

    /**
     * Prints the name of the signature sandwich, then all the details.
     *
     * @param out where to print it
     */
    @Override
    public void printSummary(java.io.PrintStream out) {
        out.println("Signature Sandwich: " + name);
        super.printSummary(out);
    }
}

