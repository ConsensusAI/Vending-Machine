package com.sg.vendingmachine.dto;

import java.math.BigDecimal;

public class ItemDto {
    private final String name;
    private final BigDecimal cost;
    private int stock;

    public ItemDto(String name, BigDecimal cost, int stock) {
        this.name = name;
        this.cost = cost;
        this.stock = stock;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public int getStock() {
        return stock;
    }

    public void reduceStock() {
        this.stock--;
    }
}
