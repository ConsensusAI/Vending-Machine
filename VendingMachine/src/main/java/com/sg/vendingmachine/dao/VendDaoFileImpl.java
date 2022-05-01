package com.sg.vendingmachine.dao;

import com.sg.vendingmachine.dto.ItemDto;
import com.sg.vendingmachine.dto.ItemInventory;

import java.util.ArrayList;
import java.util.List;

public class VendDaoFileImpl implements VendDao {

    private ItemInventory inventory;

    public VendDaoFileImpl(ItemInventory inventory) {
        this.inventory = inventory;
    }

    @Override
    public List<ItemDto> getAllItems() {
        return inventory.getAllItems();
    }

    @Override
    public ItemDto getItem(String name) {

        return null;
    }

    @Override
    public int getItemStock(String name) {
        return 0;
    }

    @Override
    public void reduceItemStock(String name) {

    }
}
