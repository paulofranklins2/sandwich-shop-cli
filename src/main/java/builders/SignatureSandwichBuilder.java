package builders;

import data.SignatureSandwiches;
import models.SignatureSandwich;

import java.util.List;

import static utils.ConsolePrinter.printHeader;
import static utils.ConsolePrinter.printLine;
import static utils.UserInputUtils.intPrompt;

public class SignatureSandwichBuilder {

    public SignatureSandwich build() {
        List<SignatureSandwich> options = SignatureSandwiches.getAll();

        printHeader("Signature Sandwiches");
        for (int i = 0; i < options.size(); i++) {
            System.out.printf("[%d] - %s%n", i, options.get(i).getName());
        }

        int index = intPrompt("Choose: ");
        if (index >= 0 && index < options.size()) {
            return options.get(index);
        }

        printLine("Invalid selection.");
        return null;
    }
}
