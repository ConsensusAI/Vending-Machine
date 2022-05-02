package com.sg.vendingmachine.ui;

import com.sg.vendingmachine.dto.ItemDto;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class VendView {

    private UserIO io;

    public VendView(UserIO io) {
        this.io = io;
    }

    public void printWelcomeBanner() {
        io.print("*******************************************");
        io.print("Welcome to the Smart Vending MachineTM Plus");
        io.print("");
    }

    public void printItems(Map<String, ItemDto> items) {
        io.print("******Current Inventory******");
        items.entrySet()
                .forEach(x -> io.print(x.getValue().getName() + ", $" + x.getValue().getCost() +
                        ", " + x.getValue().getStock() + " remaining."));
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

    public int printItemsAndGetSelection(Map<String, ItemDto> items) {
        io.print("");
        io.print("================================");
        io.print("Which item would you like to buy?");
        int max = items.size() + 1;
        items.entrySet()
                .forEach(x -> io.print(x.getKey() + ". " + x.getValue().getName()));
        io.print(max + ". Exit");
        return io.readInt("Please select from the above choices.", 1, max);
    }

    public void printUnknown() {
        io.print("Unknown Command!!");
    }

    public void printExitMessage() {
        io.print("Goodbye!!");
    }

}
