package com.sg.vendingmachine.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ItemInventory {
    private BigDecimal CHOCOLATE_PRICE = new BigDecimal("1.25");
    private BigDecimal SPORTS_PRICE = new BigDecimal("3.25");
    private BigDecimal WATER_PRICE = new BigDecimal("0.99");
    private BigDecimal GRANOLA_PRICE = new BigDecimal("1.10");
    private BigDecimal CHIPS_PRICE = new BigDecimal("1.59");
    private ItemDto CHOCOLATE_BAR;
    private ItemDto SPORTS_DRINK;
    private ItemDto WATER_BOTTLE;
    private ItemDto GRANOLA_BAR;
    private ItemDto CHIPS;
    private List<ItemDto> allItems;

    public ItemInventory() {
        this.CHOCOLATE_BAR = new ItemDto("1","Chocolate Bar", CHOCOLATE_PRICE, 12);
        this.SPORTS_DRINK = new ItemDto("2","Sports Drink", SPORTS_PRICE, 4);
        this.WATER_BOTTLE = new ItemDto("3","Water Bottle", WATER_PRICE, 5);
        this.GRANOLA_BAR = new ItemDto("4","Granola Bar", GRANOLA_PRICE, 8);
        this.CHIPS = new ItemDto("5","Potato Chips", CHIPS_PRICE, 6);
        this.allItems = new ArrayList<>();
        allItems.add(CHOCOLATE_BAR);
        allItems.add(SPORTS_DRINK);
        allItems.add(WATER_BOTTLE);
        allItems.add(GRANOLA_BAR);
        allItems.add(CHIPS);
    }

    public ItemDto getCHIPS() {
        return CHIPS;
    }

    public List<ItemDto> getAllItems() {
        return allItems;
    }
}