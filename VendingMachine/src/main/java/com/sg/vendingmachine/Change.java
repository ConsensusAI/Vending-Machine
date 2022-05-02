package com.sg.vendingmachine;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Change {

    private BigDecimal amountInPennies;
    private final String QUARTER_VALUE = Coins.QUARTER.toString();
    private final String DIME_VALUE = Coins.DIME.toString();
    private final String NICKEL_VALUE = Coins.NICKEL.toString();

    public Change(BigDecimal amount) {
        amountInPennies = amount.multiply(new BigDecimal("100"));
    }

    public int returnQuarters() {
        BigDecimal quarters = amountInPennies.divide(new BigDecimal(QUARTER_VALUE),RoundingMode.HALF_DOWN);
        setAmountInPennies(amountInPennies.subtract(quarters));
        return quarters.intValue();
    }

    public int returnDimes() {
        BigDecimal dimes = amountInPennies.divide(new BigDecimal(DIME_VALUE),RoundingMode.HALF_DOWN);
        setAmountInPennies(amountInPennies.subtract(dimes));
        return dimes.intValue();
    }


    public int returnNickels() {
        BigDecimal nickels = amountInPennies.divide(new BigDecimal(NICKEL_VALUE),RoundingMode.HALF_DOWN);
        setAmountInPennies(amountInPennies.subtract(nickels));
        return nickels.intValue();
    }

    public int returnPennies() {
        return this.amountInPennies.intValue();
    }

    public void setAmountInPennies(BigDecimal amountInPennies) {
        this.amountInPennies = amountInPennies;
    }

    public BigDecimal getAmountInPennies() {
        return amountInPennies;
    }
}
