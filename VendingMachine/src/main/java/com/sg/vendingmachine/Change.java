package com.sg.vendingmachine;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Change {

    private BigDecimal amountInPennies;
    private static final String QUARTER_VALUE = Coins.QUARTER.getValue();
    private static final String DIME_VALUE = Coins.DIME.getValue();
    private static final String NICKEL_VALUE = Coins.NICKEL.getValue();
    private static final String PENNY_VALUE = Coins.PENNY.getValue();

    public Change(BigDecimal amount) {
        amountInPennies = amount.multiply(new BigDecimal("100"));
    }

    public int returnQuarters() {
        BigDecimal quarters = amountInPennies.divide(new BigDecimal(QUARTER_VALUE), 0, RoundingMode.DOWN);
        setAmountInPennies(amountInPennies.subtract(quarters.multiply(new BigDecimal(QUARTER_VALUE))));
        return quarters.intValue();
    }

    public int returnDimes() {
        BigDecimal dimes = amountInPennies.divide(new BigDecimal(DIME_VALUE), 0, RoundingMode.DOWN);
        setAmountInPennies(amountInPennies.subtract(dimes.multiply(new BigDecimal(DIME_VALUE))));
        return dimes.intValue();
    }


    public int returnNickels() {
        BigDecimal nickels = amountInPennies.divide(new BigDecimal(NICKEL_VALUE), 0, RoundingMode.DOWN);
        setAmountInPennies(amountInPennies.subtract(nickels.multiply(new BigDecimal(NICKEL_VALUE))));
        return nickels.intValue();
    }

    public int returnPennies() {
        BigDecimal pennies = amountInPennies.divide(new BigDecimal(PENNY_VALUE), 0, RoundingMode.DOWN);
        return this.amountInPennies.intValue();
    }

    public void setAmountInPennies(BigDecimal amountInPennies) {
        this.amountInPennies = amountInPennies;
    }

    public BigDecimal getAmountInPennies() {
        return amountInPennies;
    }
}
