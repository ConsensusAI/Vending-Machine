package com.sg.vendingmachine.ui;

import com.sg.vendingmachine.dto.ItemDto;

import java.math.BigDecimal;
import java.util.List;

public class VendView {

    private final UserIO io;

    public VendView(UserIO io) {
        this.io = io;
    }

    public void printWelcomeBanner() {
        io.print("*******************************************");
        io.print("Welcome to the Smart Vending MachineTM Plus");
        io.print("");
    }

    public void printItems(List<ItemDto> items) {
        io.print("******Current Inventory******");
        items.stream()
                .filter(x -> x.getStock() > 0)
                .forEach(x -> io.print(x.getName() + ", $" + x.getCost() +
                        ", " + x.getStock() + " remaining."));
        io.print("");
    }

    public int printMenuAndGetSelection() {
        io.print("Main Menu");
        io.print("What would you like to do?");
        io.print("");
        io.print("1. Purchase an item.");
        io.print("2. Exit");

        return io.readInt("Please select from the above choices.", 1, 2);
    }

    public BigDecimal promptMoneyInserted() {
        return new BigDecimal(io.readString("How much money would you like to insert? (Please enter a value)"));
    }

    public int printItemsAndGetSelection(List<ItemDto> items) {
        io.print("");
        io.print("================================");
        io.print("Which item would you like to buy?");
        int max = items.size() + 1;
        items.stream()
                .filter(x -> x.getStock() > 0)
                .forEach(x -> io.print(x.getId() + ". " + x.getName()));
        io.print(max + ". Exit");
        return io.readInt("Please select from the above choices.", 1, max);
    }

    public void printMoney(BigDecimal money) {
        io.print("YOU HAVE: $" + money + ".");
        io.print("");
    }

    public void printChange(String moneyReturned) {
        io.print(moneyReturned);
    }

    public void printUnknown() {
        io.print("Unknown Command!!");
    }

    public void printErrorMessage(String errorMsg) {
        io.print("=== ERROR ===");
        io.print(errorMsg);
    }

    public void printExitMessage() {
        io.print("Goodbye!!");
    }

}
