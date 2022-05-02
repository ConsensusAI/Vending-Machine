package com.sg.vendingmachine.service;

import com.sg.vendingmachine.dao.VendAuditDao;
import com.sg.vendingmachine.dao.VendInventoryDao;
import com.sg.vendingmachine.dao.VendPersistenceException;
import com.sg.vendingmachine.dto.ItemDto;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TransactionServiceTest {
    private TransactionService transactionService;

    public TransactionServiceTest() {
        VendInventoryDao dao = new VendInventoryDaoStubImpl();
        VendAuditDao auditDao = new VendAuditDaoStubImpl();

        transactionService = new TransactionService(dao, auditDao);
    }

    @Test
    public void testOutOfStock() throws Exception {
        ItemDto testItem = new ItemDto("1");
        testItem.setName("Chips");
        testItem.setCost(new BigDecimal("9.99"));
        testItem.setStock(0);

        BigDecimal testMoney = new BigDecimal("100.00");
        VendInventoryDao testDao = new VendInventoryDaoStubImpl(testItem);
        VendAuditDao testAuditDao = new VendAuditDaoStubImpl();
        TransactionService testService = new TransactionService(testDao, testAuditDao);

        try {
            testService.subtractMoney(testMoney, testItem.getId());
            fail("Expected NoItemInventoryException was not thrown.");
        } catch (VendPersistenceException
                | InsufficientFundsException e) {
            fail("Incorrect exception was thrown.");
        } catch (NoStockException e) {
            return;
        }
    }

    @Test
    public void testInsufficientFunds() throws Exception {
        BigDecimal testMoney = new BigDecimal("0.01");

        try {
            transactionService.subtractMoney(testMoney, "1");
            fail("Expected InsufficientfundsException was not thrown.");
        } catch (VendPersistenceException
        | NoStockException e) {
            fail("Incorrect exception was thrown.");
        } catch (InsufficientFundsException e) {
            return;
        }
    }

    @Test
    public void testChangeReturned() throws Exception {
        BigDecimal testMoney = new BigDecimal("13.93");

        BigDecimal returnedMoney = transactionService.subtractMoney(testMoney, "1");
        String expected = "Your change: \n15 Quarters\n1 Dime\n1 Nickel\n4 Pennies";
        String output = transactionService.getChange(returnedMoney);
        assertEquals(expected, output, "The change should be 15 Quarters, 1 Dime, 1 Nickel, 4 Pennies");
    }
}
