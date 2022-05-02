package com.sg.vendingmachine;

import com.sg.vendingmachine.controller.VendController;
import com.sg.vendingmachine.dao.VendAuditDao;
import com.sg.vendingmachine.dao.VendAuditDaoFileImpl;
import com.sg.vendingmachine.dao.VendDao;
import com.sg.vendingmachine.dao.VendDaoFileImpl;
import com.sg.vendingmachine.service.VendServiceLayer;
import com.sg.vendingmachine.service.VendServiceLayerImpl;
import com.sg.vendingmachine.ui.UserIO;
import com.sg.vendingmachine.ui.UserIOConsoleImpl;
import com.sg.vendingmachine.ui.VendView;

public class App {

    public static void main(String[] args) {
        UserIO io = new UserIOConsoleImpl();
        VendView view = new VendView(io);
        VendDao dao = new VendDaoFileImpl();
        VendAuditDao auditDao = new VendAuditDaoFileImpl();
        VendServiceLayer service = new VendServiceLayerImpl(dao, auditDao);
        VendController controller = new VendController(service, view);
        controller.run();
    }
}
