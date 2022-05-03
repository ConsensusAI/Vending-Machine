package com.sg.vendingmachine.system;

import com.sg.vendingmachine.inventory.InventoryService;
import com.sg.vendingmachine.system.controller.VendController;
import com.sg.vendingmachine.audit.AuditDao;
import com.sg.vendingmachine.audit.AuditDaoTxtImpl;
import com.sg.vendingmachine.inventory.InventoryDao;
import com.sg.vendingmachine.inventory.InventoryDaoTxtImpl;
import com.sg.vendingmachine.audit.AuditServiceImpl;
import com.sg.vendingmachine.transaction.ChangeCalculator;
import com.sg.vendingmachine.transaction.TransactionService;
import com.sg.vendingmachine.inventory.InventoryServiceImpl;
import com.sg.vendingmachine.system.ui.UserIO;
import com.sg.vendingmachine.system.ui.UserIOConsoleImpl;
import com.sg.vendingmachine.system.ui.VendView;

public class App {

    public static void main(String[] args) {
        UserIO io = new UserIOConsoleImpl();
        VendView view = new VendView(io);
        InventoryDao inventoryDao = new InventoryDaoTxtImpl();
        AuditDao auditDao = new AuditDaoTxtImpl();
        InventoryService inventoryService = new InventoryServiceImpl(inventoryDao);
        AuditServiceImpl auditService = new AuditServiceImpl(auditDao);
        TransactionService transactionService = new TransactionService(inventoryService, auditService, new ChangeCalculator());
        VendController controller = new VendController(inventoryService, view, transactionService);
        controller.run();
    }
}
