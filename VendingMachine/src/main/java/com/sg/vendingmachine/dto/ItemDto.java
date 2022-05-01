package com.sg.vendingmachine.dto;

import java.math.BigDecimal;

public class ItemDto {
    private final String id;
    private final String name;
    private final BigDecimal cost;
    private int stock;

    public ItemDto(String id, String name, BigDecimal cost, int stock) {
        this.id = id;
        this.name = name;
        this.cost = cost;
        this.stock = stock;
    }

    public String getId() {
        return id;
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
