package com.sg.vendingmachine.service;

import com.sg.vendingmachine.Change;
import com.sg.vendingmachine.dao.VendAuditDao;
import com.sg.vendingmachine.dao.VendDao;
import com.sg.vendingmachine.dao.VendPersistenceException;
import com.sg.vendingmachine.dto.ItemDto;

import java.math.BigDecimal;
import java.util.List;

public class VendServiceLayerImpl implements VendServiceLayer {
    VendDao dao;
    private final VendAuditDao auditDao;

    public VendServiceLayerImpl(VendDao dao, VendAuditDao auditDao) {
        this.dao = dao;
        this.auditDao = auditDao;
    }

    @Override
    public List<ItemDto> getAllItems() throws VendPersistenceException {
        return dao.getAllItems();
    }
}
