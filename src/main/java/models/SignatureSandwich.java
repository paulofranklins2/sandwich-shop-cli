package models;

import lombok.Getter;
import models.enums.BreadType;
import models.enums.SandwichSize;
import models.enums.Topping;

import java.util.List;

@Getter
public class SignatureSandwich extends Sandwich {

    private final String name;

    public SignatureSandwich(String name, SandwichSize size, BreadType bread, List<Topping> toppings, List<Topping> extras, boolean isToasted) {
        super(size, bread, toppings, extras, isToasted);
        this.name = name;
    }

    @Override
    public void printSummary(java.io.PrintStream out) {
        out.println("Signature Sandwich: " + name);
        super.printSummary(out);
    }
}
