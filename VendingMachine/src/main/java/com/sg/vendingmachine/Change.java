package com.sg.vendingmachine;

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

    /*
        Used when displaying change to be returned to the user.
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Total: $" + total + " ");
        builder.append("Quarters: " + quarters + " ");
        builder.append("Dimes: " + dimes + " ");
        builder.append("Nickels: " + nickels + " ");
        builder.append("Pennies: " + pennies);
        return builder.toString();
    }
}
