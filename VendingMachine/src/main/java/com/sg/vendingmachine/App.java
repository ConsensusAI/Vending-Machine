package com.sg.vendingmachine;

import com.sg.vendingmachine.controller.VendController;
import com.sg.vendingmachine.dao.VendAuditDao;
import com.sg.vendingmachine.dao.VendAuditDaoTxtImpl;
import com.sg.vendingmachine.dao.VendInventoryDao;
import com.sg.vendingmachine.dao.VendInventoryDaoTxtImpl;
import com.sg.vendingmachine.service.AuditService;
import com.sg.vendingmachine.service.ChangeService;
import com.sg.vendingmachine.service.InventoryService;
import com.sg.vendingmachine.service.TransactionService;
import com.sg.vendingmachine.ui.UserIO;
import com.sg.vendingmachine.ui.UserIOConsoleImpl;
import com.sg.vendingmachine.ui.VendView;

public class App {

    public static void main(String[] args) {
        UserIO io = new UserIOConsoleImpl();
        VendView view = new VendView(io);

        VendInventoryDao inventoryDao = new VendInventoryDaoTxtImpl();
        VendAuditDao auditDao = new VendAuditDaoTxtImpl();
        InventoryService inventoryService = new InventoryService(inventoryDao);
        AuditService auditService = new AuditService(auditDao);
        TransactionService transactionService = new TransactionService(inventoryService, auditService, new ChangeService());

        VendController controller = new VendController(inventoryService, view, transactionService);
        controller.run();
    }
}
