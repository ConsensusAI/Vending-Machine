package com.sg.vendingmachine.service;

import com.sg.vendingmachine.dao.VendAuditDao;
import com.sg.vendingmachine.dao.VendDao;
import org.junit.jupiter.api.Test;

class VendServiceLayerImplTest {

    private VendServiceLayer service;
    private VendDao dao;

    public VendServiceLayerImplTest() {
        VendDao dao = new VendDaoStubImpl();
        service = new VendServiceLayerImpl(dao);
    }

    @Test
    public void testGetAllItems() throws Exception {
        dao.getAllItems();
    }
}