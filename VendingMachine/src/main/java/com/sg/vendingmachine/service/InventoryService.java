package com.sg.vendingmachine.service;

import com.sg.vendingmachine.dao.VendInventoryDao;
import com.sg.vendingmachine.dao.VendPersistenceException;
import com.sg.vendingmachine.dto.ItemDto;

import java.util.List;

public class InventoryService {
    VendInventoryDao dao;

    public InventoryService(VendInventoryDao dao) {
        this.dao = dao;
    }

    public List<ItemDto> getAllItems() throws VendPersistenceException {
        return dao.getAllItems();
    }
}
