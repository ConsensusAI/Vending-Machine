package com.sg.vendingmachine.service;

import com.sg.vendingmachine.audit.AuditDao;
import com.sg.vendingmachine.audit.AuditServiceImpl;
import com.sg.vendingmachine.inventory.InventoryService;
import com.sg.vendingmachine.inventory.ItemDto;
import com.sg.vendingmachine.transaction.ChangeCalculator;
import com.sg.vendingmachine.transaction.InsufficientFundsException;
import com.sg.vendingmachine.transaction.NoStockException;
import com.sg.vendingmachine.transaction.TransactionService;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.fail;

public class TransactionServiceTest {
    private TransactionService transactionService;

    public TransactionServiceTest() {
        InventoryService inventoryService = new InventoryServiceStub();
        AuditDao auditDao = new AuditDaoStubImpl();
        AuditServiceImpl auditService = new AuditServiceImpl(auditDao);

        transactionService = new TransactionService(inventoryService, auditService, new ChangeCalculator());
    }

    @Test
    public void testCheckStock() throws Exception {
        ItemDto testItem = new ItemDto("1");
        testItem.setName("Chips");
        testItem.setCost(new BigDecimal("9.99"));
        testItem.setStock(10);

        ItemDto noStockItem = new ItemDto("1");
        noStockItem.setName("Chips");
        noStockItem.setCost(new BigDecimal("9.99"));
        noStockItem.setStock(0);

        try {
            transactionService.checkStock(testItem);
        } catch (NoStockException e) {
            fail("ERROR: NoStockException should not be thrown.");
        }

        try {
            transactionService.checkStock(noStockItem);
            fail("ERROR: Expected NoStockException should have been thrown.");
        } catch (NoStockException e) {
            return;
        }
    }

    @Test
    public void testCompareMoney() throws Exception {
        ItemDto testItem = new ItemDto("1");
        testItem.setName("Chips");
        testItem.setCost(new BigDecimal("9.99"));
        testItem.setStock(10);

        BigDecimal testMoney = new BigDecimal("15.00");
        BigDecimal notEnoughMoney = new BigDecimal("0.99");

        try {
            transactionService.compareMoney(testMoney, testItem);
        } catch (InsufficientFundsException e) {
            fail("ERROR: InsufficientFundsException should not be thrown.");
        }

        try {
            transactionService.compareMoney(notEnoughMoney, testItem);
            fail("ERROR: InsufficientFundsException should have been thrown.");
        } catch (InsufficientFundsException e) {
            return;
        }
    }

    @Test
    public void testComputeTransaction() throws Exception {}
}
