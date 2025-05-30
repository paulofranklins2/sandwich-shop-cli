package gui.helpers;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceDialog;
import utils.ConsolePrinter;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Common dialog helpers like confirmation and enum selection.
 */
public class DialogUtils {

    /**
     * Shows a yes/no confirmation dialog.
     */
    public static boolean confirm(String message) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm");
        alert.setHeaderText(null);
        alert.setContentText(message);
        return alert.showAndWait().filter(btn -> btn == ButtonType.OK).isPresent();
    }

    /**
     * Shows an alert with given type.
     */
    public static void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    /**
     * Prompts the user to pick an option from an enum list (returns enum).
     */
    public static <T extends Enum<T>> T promptEnumOption(String title, T[] values) {
        List<String> options = Arrays.stream(values)
                .map(v -> ConsolePrinter.capitalizeWords(v.name().toLowerCase().replace("_", " ")))
                .collect(Collectors.toList());

        ChoiceDialog<String> dialog = new ChoiceDialog<>(options.getFirst(), options);
        dialog.setTitle(title);
        dialog.setHeaderText("Pick one");
        dialog.setContentText("Choice:");
        Optional<String> result = dialog.showAndWait();

        return result.flatMap(choice ->
                Arrays.stream(values)
                        .filter(v -> ConsolePrinter.capitalizeWords(v.name().toLowerCase().replace("_", " ")).equals(choice))
                        .findFirst()
        ).orElse(null);
    }
}
