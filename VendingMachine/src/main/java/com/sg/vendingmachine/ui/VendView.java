package com.sg.vendingmachine.ui;

import com.sg.vendingmachine.dto.ItemDto;

import java.util.List;

public class VendView {

    private UserIO io;

    public VendView(UserIO io) {
        this.io = io;
    }

    public void printItems(List<ItemDto> items) {
        items.stream()
                .forEach(x -> io.print(x.getName() + ", $" + x.getCost() + ", " + x.getStock() + " remaining."));
    }

}
