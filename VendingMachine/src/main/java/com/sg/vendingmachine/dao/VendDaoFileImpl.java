package com.sg.vendingmachine.dao;

import com.sg.vendingmachine.dto.ItemDto;
import com.sg.vendingmachine.dto.ItemInventory;

import java.math.BigDecimal;
import java.util.Map;

public class VendDaoFileImpl implements VendDao {

    private ItemInventory inventory;
    private Map<String, ItemDto> items;

    public VendDaoFileImpl(ItemInventory inventory) {
        this.inventory = inventory;
        this.items = inventory.getAllItems();
    }

    @Override
    public Map<String, ItemDto> getAllItems() {
        return inventory.getAllItems();
    }

    @Override
    public ItemDto getItem(String id) {
        return items.get(id);
    }

    @Override
    public int getItemStock(String id) {
        return 0;
    }

    @Override
    public void reduceItemStock(String id) {

    }

    @Override
    public BigDecimal subtractMoney(BigDecimal initMoney, String itemId) {
        BigDecimal cost = getItem(itemId).getCost();
        return initMoney.subtract(cost);
    }
}
