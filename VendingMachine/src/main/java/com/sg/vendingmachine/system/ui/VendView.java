package com.sg.vendingmachine.system.ui;

import com.sg.vendingmachine.transaction.Change;
import com.sg.vendingmachine.inventory.ItemDto;

import java.math.BigDecimal;
import java.util.List;

public class VendView {

    private final UserIO io;

    public VendView(UserIO io) {
        this.io = io;
    }

    public void printWelcomeBanner() {
        io.print(UtfGraphics.STARS.getGraphic());
        io.print(UtfGraphics.VENDING_NAME.getGraphic());
        io.print("");
    }

    public void printInventory(List<ItemDto> items) {
        io.print(UtfGraphics.CURRENT_INVENTORY.getGraphic());
        printItems(items);
        io.print("");
    }

    private void printItems(List<ItemDto> items) {
        items.stream()
                .filter(x -> x.getStock() > 0)
                .forEach(x -> io.print(x.getName() + "， ＄" + x.getCost() +
                        "， " + x.getStock() + " ｒｅｍａｉｎｉｎｇ．"));
    }

    private void printThankYou() {
        io.print(UtfGraphics.THANK_YOU.getGraphic());
        printEnterToContinue();
    }

    private void printLineBreak() {
        io.print(UtfGraphics.EQUALS_BANNER.getGraphic());
    }

    public int printMenuAndGetSelection() {
        io.print(UtfGraphics.MAIN_MENU.getGraphic());
        io.print("\uFEFFＷｈａｔ　ｗｏｕｌｄ　ｙｏｕ　ｌｉｋｅ　ｔｏ　ｄｏ？");
        io.print("");
        io.print("『１．　Ｐｕｒｃｈａｓｅ　ａｎ　ｉｔｅｍ．』");
        io.print("『２．　Ｅｘｉｔ』");

        return io.readInt(UtfGraphics.PLEASE_SELECT.getGraphic(), 1, 2);
    }

    public BigDecimal promptMoneyInserted() {
        return new BigDecimal(io.readString("Ｈｏｗ　ｍｕｃｈ　ｍｏｎｅｙ　ｗｏｕｌｄ　ｙｏｕ　ｌｉｋｅ　ｔｏ　ｉｎｓｅｒｔ？" +
                "\n（Ｐｌｅａｓｅ　ｅｎｔｅｒ　ｔｈｅ　ｎｕｍｂｅｒ　ｏｆ　ｄｏｌｌａｒｓ）："));
    }

    public int printItemsAndGetSelection(List<ItemDto> items) {
        io.print("");
        printLineBreak();
        io.print("Ｗｈｉｃｈ　ｉｔｅｍ　ｗｏｕｌｄ　ｙｏｕ　ｌｉｋｅ　ｔｏ　ｂｕｙ？");
        int max = items.size() + 1;
        items.stream()
                .filter(x -> x.getStock() > 0)
                .forEach(x -> io.print(x.getId() + "． " + x.getName()));
        io.print(max + "． 【\uFEFFＥｘｉｔ】");
        return io.readInt(UtfGraphics.PLEASE_SELECT.getGraphic(), 1, max);
    }

    public void printMoney(BigDecimal money) {
        io.print("ＹＯＵ　ＨＡＶＥ：　＄" + money + "．");
        io.print("");
    }

    public void printChange(Change change) {
        io.print("");
        printLineBreak();
        printThankYou();
        io.print(UtfGraphics.HERE_IS_YOUR_CHANGE.getGraphic());
        io.print(change.toString());
        printEnterToContinue();
    }

    public void printNoChange() {
        io.print("");
        printLineBreak();
        printThankYou();
        io.print(UtfGraphics.NO_CHANGE.getGraphic());
        printEnterToContinue();
    }

    public void printUnknown() {
        io.print(UtfGraphics.UNKNOWN_COMMAND.getGraphic());
    }

    public void printErrorMessage(String errorMsg) {
        io.print("【\uFEFF＝＝＝　ＥＲＲＯＲ　＝＝＝】");
        io.print(errorMsg);
    }

    public void printExitMessage() {
        io.print("【\uFEFFＧｏｏｄｂｙｅ！！】");
    }

    public void printEnterToContinue() {
        io.readString(UtfGraphics.PRESS_ENTER.getGraphic());
    }

}
