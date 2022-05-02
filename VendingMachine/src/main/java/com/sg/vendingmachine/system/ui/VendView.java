package com.sg.vendingmachine.system.ui;

import com.sg.vendingmachine.inventory.ItemDto;
import com.sg.vendingmachine.transaction.Change;

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

    public void printInventory(List<ItemDto> items) {
        io.print("******Current Inventory******");
        printItems(items);
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
        return new BigDecimal(io.readString("How much money would you like to insert? (Please enter the number of dollars)"));
    }

    public int printItemsAndGetSelection(List<ItemDto> items) {
        io.print("");
        printLineBreak();
        io.print("Which item would you like to buy?");
        int max = items.size() + 1;
        printItems(items);
        io.print(max + ". Exit");
        return io.readInt("Please select from the above choices.", 1, max);
    }

    public void printMoney(BigDecimal money) {
        io.print("YOU HAVE: $" + money + ".");
        io.print("");
    }

    public void printChange(Change change) {
        io.print("");
        printLineBreak();
        printThankYou();
        io.print("Here's your change...");
        io.print(change.toString());
        io.print("");
    }

    public void printNoChange() {
        io.print("");
        printLineBreak();
        printThankYou();
        io.print("No change to return...");
        io.print("");
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

    private void printThankYou() {
        io.print("Thank you for your purchase!");
    }

    private void printLineBreak() {
        io.print("================================");
    }

    private void printItems(List<ItemDto> items) {
        items.stream()
                .filter(item -> item.getStock() > 0)
                .forEach(item -> io.print(item.getId() + ". " + item.getName() + ", $" + item.getCost() + ", " + item.getStock() + " remaining."));
    }
}
