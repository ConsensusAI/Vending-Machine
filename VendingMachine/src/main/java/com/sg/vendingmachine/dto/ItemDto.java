package com.sg.vendingmachine.dto;

import java.math.BigDecimal;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemDto itemDto = (ItemDto) o;
        return stock == itemDto.stock && id.equals(itemDto.id) && name.equals(itemDto.name) && cost.equals(itemDto.cost);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, cost, stock);
    }
}
