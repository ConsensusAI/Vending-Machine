package com.sg.vendingmachine;

import com.sg.vendingmachine.controller.VendController;
import com.sg.vendingmachine.dao.VendAuditDao;
import com.sg.vendingmachine.dao.VendAuditDaoTxtImpl;
import com.sg.vendingmachine.dao.VendInventoryDao;
import com.sg.vendingmachine.dao.VendInventoryDaoTxtImpl;
import com.sg.vendingmachine.service.TransactionService;
import com.sg.vendingmachine.service.InventoryService;
import com.sg.vendingmachine.ui.UserIO;
import com.sg.vendingmachine.ui.UserIOConsoleImpl;
import com.sg.vendingmachine.ui.VendView;

public class App {

    public static void main(String[] args) {
        UserIO io = new UserIOConsoleImpl();
        VendView view = new VendView(io);
        VendInventoryDao inventoryDao = new VendInventoryDaoTxtImpl();
        VendAuditDao auditDao = new VendAuditDaoTxtImpl();
        InventoryService service = new InventoryService(inventoryDao);
        TransactionService transactionService = new TransactionService(inventoryDao, auditDao);
        VendController controller = new VendController(service, view, transactionService);
        controller.run();
    }
}
