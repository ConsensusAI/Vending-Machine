package com.sg.vendingmachine.transaction;

import java.math.BigDecimal;

public class Change {

    private final BigDecimal total;
    private final int quarters;
    private final int dimes;
    private final int nickels;
    private final int pennies;

    public Change(BigDecimal total, int quarters, int dimes, int nickels, int pennies) {
        this.total = total;
        this.quarters = quarters;
        this.dimes = dimes;
        this.nickels = nickels;
        this.pennies = pennies;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public int getQuarters() {
        return quarters;
    }

    public int getDimes() {
        return dimes;
    }

    public int getNickels() {
        return nickels;
    }

    public int getPennies() {
        return pennies;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Total: $" + total + " ");
        builder.append(Coins.QUARTER.getName() + ": " + quarters + " ");
        builder.append(Coins.DIME.getName() + ": " + dimes + " ");
        builder.append(Coins.NICKEL.getName() + ": " + nickels + " ");
        builder.append(Coins.PENNY.getName() + ": " + pennies);
        return builder.toString();
    }
}
