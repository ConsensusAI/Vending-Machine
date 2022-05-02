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
        this.CHOCOLATE_BAR = new ItemDto("1");
        this.SPORTS_DRINK = new ItemDto("2");
        this.WATER_BOTTLE = new ItemDto("3");
        this.GRANOLA_BAR = new ItemDto("4");
        this.CHIPS = new ItemDto("5");
        this.CHOCOLATE_BAR.setName("Chocolate Bar");
        this.CHOCOLATE_BAR.setCost(CHOCOLATE_PRICE);
        this.CHOCOLATE_BAR.setStock(12);
        this.SPORTS_DRINK.setName("Sports Drink");
        this.SPORTS_DRINK.setCost(SPORTS_PRICE);
        this.SPORTS_DRINK.setStock(4);
        this.WATER_BOTTLE.setName("Water Bottle");
        this.WATER_BOTTLE.setCost(WATER_PRICE);
        this.WATER_BOTTLE.setStock(5);
        this.GRANOLA_BAR.setName("Granola Bar");
        this.GRANOLA_BAR.setCost(GRANOLA_PRICE);
        this.GRANOLA_BAR.setStock(8);
        this.CHIPS.setName("Potato Chips");
        this.CHIPS.setCost(CHIPS_PRICE);
        this.CHIPS.setStock(6);
        allItems = new HashMap<>();
        allItems.put(CHOCOLATE_BAR.getId(), CHOCOLATE_BAR);
        allItems.put(SPORTS_DRINK.getId(), SPORTS_DRINK);
        allItems.put(WATER_BOTTLE.getId(), WATER_BOTTLE);
        allItems.put(GRANOLA_BAR.getId(), GRANOLA_BAR);
        allItems.put(CHIPS.getId(), CHIPS);
    }

    public Map<String, ItemDto> getAllItems() {
        return allItems;
    }
}