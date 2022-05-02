package com.sg.vendingmachine.dto;

import java.math.BigDecimal;

public class ItemDto {
    private String id;
    private String name;
    private BigDecimal cost;
    private int stock;

    public ItemDto(String id) {
        this.id = id;
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

    public void setName(String name) {
        this.name = name;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

}
