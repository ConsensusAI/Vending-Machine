package com.sg.vendingmachine.ui;

import com.sg.vendingmachine.dto.ItemDto;

import java.math.BigDecimal;
import java.util.List;

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

    public void printItems(List<ItemDto> items) {
        io.print("******Current Inventory******");
        items.forEach(x -> io.print(x.getName() + ", $" + x.getCost() + ", " + x.getStock() + " remaining."));
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
        items.forEach(x -> io.print(x.getId() + ". " + x.getName()));
        io.print("6. Exit");
        return io.readInt("Please select from the above choices.", 1, 6);
    }

    public void printUnknown() {
        io.print("Unknown Command!!");
    }

    public void printExitMessage() {
        io.print("Goodbye!!");
    }

}
