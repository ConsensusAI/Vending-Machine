package com.sg.vendingmachine.system.ui;

import utils.GenerateIntegerInput;

import java.util.Scanner;

public class UserIOConsoleImpl implements UserIO{
    private static final Scanner scanner = new Scanner(System.in);
    private static final GenerateIntegerInput userInput = new GenerateIntegerInput();

    @Override
    public void print(String message) {
        System.out.println(message);
    }

    @Override
    public String readString(String prompt) {
        System.out.println(prompt);
        return scanner.nextLine();
    }

    @Override
    public int readInt(String prompt) {
        System.out.println(prompt);
        return userInput.generateIntegerInput();
    }

    @Override
    public int readInt(String prompt, int min, int max) {
        while (true) {
            System.out.println(prompt);
            int response = userInput.generateIntegerInput();
            if (response < min || response > max) {
                continue;
            }
            return response;
        }
    }

    @Override
    public double readDouble(String prompt) {
        System.out.println(prompt);
        return scanner.nextDouble();
    }

    @Override
    public double readDouble(String prompt, double min, double max) {
        while (true) {
            System.out.println(prompt);
            double response = scanner.nextDouble();
            if (response < min || response > max) {
                continue;
            }
            return response;
        }
    }

    @Override
    public float readFloat(String prompt) {
        System.out.println(prompt);
        return scanner.nextFloat();
    }

    @Override
    public float readFloat(String prompt, float min, float max) {
        while (true) {
            System.out.println(prompt);
            float response = scanner.nextFloat();
            if (response < min || response > max) {
                continue;
            }
            return response;
        }
    }

    @Override
    public long readLong(String prompt) {
        System.out.println(prompt);
        return scanner.nextLong();
    }

    @Override
    public long readLong(String prompt, long min, long max) {
        while (true) {
            System.out.println(prompt);
            long response = scanner.nextLong();
            if (response < min || response > max) {
                continue;
            }
            return response;
        }
    }
}
