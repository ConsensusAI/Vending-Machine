package com.sg.vendingmachine;

import com.sg.vendingmachine.controller.VendController;
import com.sg.vendingmachine.dao.VendDao;
import com.sg.vendingmachine.dao.VendDaoFileImpl;
import com.sg.vendingmachine.dto.ItemInventory;
import com.sg.vendingmachine.ui.UserIO;
import com.sg.vendingmachine.ui.UserIOConsoleImpl;
import com.sg.vendingmachine.ui.VendView;

public class App {

    public static void main(String[] args) {
        UserIO io = new UserIOConsoleImpl();
        VendView view = new VendView(io);
        ItemInventory inventory = new ItemInventory();
        VendDao dao = new VendDaoFileImpl(inventory);
        VendController controller = new VendController(view, dao);
        controller.run();
    }
}
