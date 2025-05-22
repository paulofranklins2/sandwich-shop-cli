package ui;

public class UserInterface {

    private void homeScreen() {
        System.out.println("=== Home Screen ===");
        System.out.println("[1] - Add ");
        System.out.println("[0] - Exit");
    }

    public void orderScreen() {
        System.out.println("=== Order Screen ===");
        System.out.println("[1] - Add Sandwich");
        System.out.println("[2] - Add Drink");
        System.out.println("[3] - Add Chips");
        System.out.println("[4] - Checkout");
        System.out.println("[0] - Cancel Order");
    }

    //Home Screen
    //o The home screen should give the user the following options. The
    //application should continue to run until the user chooses to exit.
    //§ 1) New Order
    //§ 0) Exit - exit the application
    //• Order Screen - All entries should show the newest entries first
    //o 1) Add Sandwich
    //o 2) Add Drink
    //o 3) Add Chips
    //o 4) Checkout
    //o 0) Cancel Order - delete the order and go back to the home page
    //• Add Sandwich - the add sandwich screen will walk the user through
    //several options to create the sandwich
    //o Select your bread:
    //o Sandwich size:
    //o Toppings: - the user should be able to add extras of each topping
    //§ Meat:
    //§ Cheese:
    //§ Other toppings:
    //§ Select sauces:
    //o Would you like the sandwich toasted?
    //• Add Drink - select drink size and flavor
    //• Add Chips - select chip type
    //• Checkout - display the order details and the price
    //o Confirm - create the receipt file and go back to the home screen
    //o Cancel - delete order and go back to the home screen
}
