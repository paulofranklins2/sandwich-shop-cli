package gui.util;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

/**
 * A VBox with pre-configured spacing and alignment for uniform layout.
 */
public class StyledVBox extends VBox {
    public StyledVBox() {
        setSpacing(30);
        setPadding(new Insets(40));
        setAlignment(Pos.CENTER);
    }

    /**
     * Applies a consistent style to multiple buttons.
     */
    public void styleButton(Button... buttons) {
        for (Button b : buttons) {
            b.setPrefWidth(280);
            b.setPrefHeight(50);
            b.setStyle("-fx-font-size: 16px; -fx-background-color: #ffe5b4; -fx-text-fill: #3a3a3a; -fx-background-radius: 10;");
        }
    }
}