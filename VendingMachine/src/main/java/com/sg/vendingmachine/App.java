package com.sg.vendingmachine;

import com.sg.vendingmachine.controller.VendController;
import com.sg.vendingmachine.dao.VendDao;
import com.sg.vendingmachine.dao.VendDaoFileImpl;
import com.sg.vendingmachine.ui.UserIO;
import com.sg.vendingmachine.ui.UserIOConsoleImpl;
import com.sg.vendingmachine.ui.VendView;

public class App {

    public static void main(String[] args) {
        UserIO io = new UserIOConsoleImpl();
        VendView view = new VendView(io);
        VendDao dao = new VendDaoFileImpl();
        VendController controller = new VendController(view, dao);
        controller.run();
    }
}
