package models.enums;

public enum SandwichSize {
    FOUR_INCH("4\""),
    EIGHT_INCH("8\""),
    TWELVE_INCH("12\"");

    private final String label;

    SandwichSize(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return label + " inches";
    }
}
