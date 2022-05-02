package com.sg.vendingmachine.service;

import com.sg.vendingmachine.Change;

import java.math.BigDecimal;

public class ChangeService {

    public String returnChange(BigDecimal amount) {
        Change change = new Change(amount);
        int quarters = change.returnQuarters();
        int dimes = change.returnDimes();
        int nickels = change.returnNickels();
        int pennies = change.returnPennies();
        String moneyReturned = "Your change: ";

        moneyReturned += returnQuarters(quarters);
        moneyReturned += returnDimes(dimes);
        moneyReturned += returnNickels(nickels);
        moneyReturned += returnPennies(pennies);

        return moneyReturned;
    }

    private String returnQuarters(int quarters) {
        String quartersReturned = "";
        if (quarters > 1) {
            quartersReturned += "\n" + quarters + " Quarters";
        } else if (quarters == 1) {
            quartersReturned += "\n" + quarters + " Quarter";
        }
        return quartersReturned;
    }

    private String returnDimes(int dimes) {
        String dimesReturned = "";
        if (dimes > 1) {
            dimesReturned += "\n" + dimes + " Dimes";
        } else if (dimes == 1) {
            dimesReturned += "\n" + dimes + " Dime";
        }
        return dimesReturned;
    }

    private String returnNickels(int nickels) {
        String nickelsReturned = "";
        if (nickels > 1) {
            nickelsReturned += "\n" + nickels + " Nickels";
        } else if (nickels == 1) {
            nickelsReturned += "\n" + nickels + " Nickel";
        }
        return nickelsReturned;
    }

    private String returnPennies(int pennies) {
        String penniesReturned = "";
        if (pennies == 1) {
            penniesReturned += "\n" + pennies + " Penny";
        } else {
            penniesReturned += "\n" + pennies + " Pennies";
        }
        return penniesReturned;
    }

}
