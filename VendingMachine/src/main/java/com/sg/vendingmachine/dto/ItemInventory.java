package com.sg.vendingmachine.dto;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

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
    private Map<String, ItemDto> allItems;

    public ItemInventory() {
        this.CHOCOLATE_BAR = new ItemDto("Chocolate Bar", CHOCOLATE_PRICE, 12);
        this.SPORTS_DRINK = new ItemDto("Sports Drink", SPORTS_PRICE, 4);
        this.WATER_BOTTLE = new ItemDto("Water Bottle", WATER_PRICE, 5);
        this.GRANOLA_BAR = new ItemDto("Granola Bar", GRANOLA_PRICE, 8);
        this.CHIPS = new ItemDto("Potato Chips", CHIPS_PRICE, 6);
        allItems = new HashMap<>();
        allItems.put("1", CHOCOLATE_BAR);
        allItems.put("2", SPORTS_DRINK);
        allItems.put("3", WATER_BOTTLE);
        allItems.put("4", GRANOLA_BAR);
        allItems.put("5", CHIPS);
    }

    public Map<String, ItemDto> getAllItems() {
        return allItems;
    }
}