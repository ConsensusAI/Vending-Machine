package com.sg.vendingmachine.service;

import com.sg.vendingmachine.dao.VendDao;
import com.sg.vendingmachine.dao.VendPersistenceException;
import com.sg.vendingmachine.dto.ItemDto;

import java.util.List;

public class VendServiceLayerImpl implements VendServiceLayer {
    VendDao dao;

    public VendServiceLayerImpl(VendDao dao) {
        this.dao = dao;
    }

    @Override
    public List<ItemDto> getAllItems() throws VendPersistenceException {
        return dao.getAllItems();
    }
}
