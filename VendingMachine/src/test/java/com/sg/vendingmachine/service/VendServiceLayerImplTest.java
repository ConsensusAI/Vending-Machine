package com.sg.vendingmachine.service;

import com.sg.vendingmachine.dao.VendAuditDao;
import com.sg.vendingmachine.dao.VendDao;
import com.sg.vendingmachine.dao.VendPersistenceException;
import com.sg.vendingmachine.dto.ItemDto;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class VendServiceLayerImplTest {

    private VendServiceLayer service;

    public VendServiceLayerImplTest() {
        VendDao dao = new VendDaoStubImpl();
        VendAuditDao auditDao = new VendAuditDaoStubImpl();

        service = new VendServiceLayerImpl(dao, auditDao);
    }

    @Test
    public void testGetAllItems() {

    }
}