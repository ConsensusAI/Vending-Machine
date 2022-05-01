package utils;

import java.util.Scanner;

public class GenerateIntegerInput {
    static Scanner scanner = new Scanner(System.in);

    public int generateIntegerInput() {
        Integer returnedInteger = null;

        while (returnedInteger == null) {
            try {
                returnedInteger = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Not a number, try again! ");
            }
        }

        return returnedInteger;
    }
}
