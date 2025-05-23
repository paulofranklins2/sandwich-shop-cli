package Utils;

import java.util.InputMismatchException;
import java.util.Scanner;

public class UserInputUtils {
    public static final Scanner scanner = new Scanner(System.in);

    public static int intPrompt(String message) {
        while (true) {
            try {
                System.out.print(message);
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number. ");
                scanner.nextLine();
            }
        }
    }
}
