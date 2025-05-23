package models.enums;

/**
 * Represents the available size options for a {@link models.Sandwich},
 * each with a user-friendly display label.
 */
public enum SandwichSize {
    FOUR_INCH("4\""),
    EIGHT_INCH("8\""),
    TWELVE_INCH("12\"");

    private final String label;

    SandwichSize(String label) {
        this.label = label;
    }

    /**
     * Returns a user-friendly string representation of the sandwich size.
     *
     * @return the formatted size label followed by "inches"
     */
    @Override
    public String toString() {
        return label + " inches";
    }
}
