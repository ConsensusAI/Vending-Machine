package com.sg.vendingmachine.system;

import com.sg.vendingmachine.audit.AuditDaoTxtImpl;
import com.sg.vendingmachine.audit.AuditService;
import com.sg.vendingmachine.inventory.InventoryDaoTxtImpl;
import com.sg.vendingmachine.inventory.InventoryService;
import com.sg.vendingmachine.system.controller.VendController;
import com.sg.vendingmachine.system.ui.UserIO;
import com.sg.vendingmachine.system.ui.UserIOConsoleImpl;
import com.sg.vendingmachine.system.ui.VendView;
import com.sg.vendingmachine.transaction.ChangeCalculator;
import com.sg.vendingmachine.transaction.TransactionService;

public class App {

    public static void main(String[] args) {
        UserIO io = new UserIOConsoleImpl();
        VendView view = new VendView(io);

        InventoryService inventoryService = new InventoryService(new InventoryDaoTxtImpl());
        AuditService auditService = new AuditService(new AuditDaoTxtImpl());
        TransactionService transactionService = new TransactionService(inventoryService, auditService, new ChangeCalculator());

        VendController controller = new VendController(inventoryService, view, transactionService);
        controller.run();
    }
}
