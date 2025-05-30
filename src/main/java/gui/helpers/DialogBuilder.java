package gui.helpers;

import javafx.scene.control.ChoiceDialog;
import models.Chip;
import models.Drink;
import models.Sandwich;
import models.SignatureSandwich;
import models.enums.*;
import utils.ConsolePrinter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Builds input dialogs for various menu items.
 */
public class DialogBuilder {

    public static SignatureSandwich buildSignatureSandwich(List<SignatureSandwich> options) {
        ChoiceDialog<SignatureSandwich> dialog = new ChoiceDialog<>(options.getFirst(), options);
        dialog.setTitle("Choose Signature Sandwich");
        dialog.setHeaderText("Pick a pre-made sandwich");
        dialog.setContentText("Sandwich:");

        Optional<SignatureSandwich> result = dialog.showAndWait();

        return result.map(base -> new SignatureSandwich(
                base.getName(),
                base.getSandwichSize(),
                base.getBreadType(),
                new ArrayList<>(base.getToppings()),
                new ArrayList<>(base.getExtraToppings()),
                base.getIsToasted()
        )).orElse(null);
    }

    public static Chip buildChip() {
        List<String> options = Arrays.stream(ChipFlavor.values())
                .map(f -> ConsolePrinter.capitalizeWords(f.name().toLowerCase().replace("_", " ")))
                .collect(Collectors.toList());

        ChoiceDialog<String> dialog = new ChoiceDialog<>(options.getFirst(), options);
        dialog.setTitle("Choose Chips");
        dialog.setHeaderText("Pick a chip flavor");
        dialog.setContentText("Flavor:");

        Optional<String> result = dialog.showAndWait();

        return result.flatMap(flavorStr ->
                Arrays.stream(ChipFlavor.values())
                        .filter(f -> ConsolePrinter.capitalizeWords(f.name().toLowerCase().replace("_", " ")).equals(flavorStr))
                        .findFirst()
                        .map(Chip::new)
        ).orElse(null);
    }

    public static Drink buildDrink() {
        DrinkSize size = DialogUtils.promptEnumOption("Drink Size", DrinkSize.values());
        if (size == null) return null;

        DrinkFlavor flavor = DialogUtils.promptEnumOption("Drink Flavor", DrinkFlavor.values());
        if (flavor == null) return null;

        return new Drink(size, flavor);
    }

    public static Sandwich buildCustomSandwich() {
        BreadType bread = DialogUtils.promptEnumOption("Bread Type", BreadType.values());
        SandwichSize size = DialogUtils.promptEnumOption("Sandwich Size", SandwichSize.values());
        if (bread == null || size == null) return null;

        List<Topping> toppings = chooseToppings(false);
        List<Topping> extraToppings = DialogUtils.confirm("Add extra toppings?") ? chooseToppings(true) : new ArrayList<>();
        boolean isToasted = DialogUtils.confirm("Do you want your sandwich toasted?");

        return new Sandwich(size, bread, toppings, extraToppings, isToasted);
    }

    private static List<Topping> chooseToppings(boolean isExtra) {
        List<Topping> selected = new ArrayList<>();

        for (ToppingType type : ToppingType.values()) {
            if (DialogUtils.confirm("Add " + (isExtra ? "extra " : "") + type.name().toLowerCase() + " toppings?")) {
                selected.addAll(promptToppings("Choose " + (isExtra ? "Extra " : "") + type.name() + " Toppings", Topping.getByType(type)));
            }
        }

        return selected;
    }

    private static List<Topping> promptToppings(String title, List<Topping> options) {
        List<Topping> selected = new ArrayList<>();
        List<String> names = options.stream().map(Enum::name).collect(Collectors.toList());

        ChoiceDialog<String> dialog = new ChoiceDialog<>(names.get(0), names);
        dialog.setTitle(title);
        dialog.setHeaderText(null);
        dialog.setContentText("Pick a topping:");

        while (true) {
            Optional<String> result = dialog.showAndWait();
            if (result.isEmpty()) break;
            selected.add(Topping.valueOf(result.get()));
            if (!DialogUtils.confirm("Add another topping?")) break;
        }

        return selected;
    }
}
