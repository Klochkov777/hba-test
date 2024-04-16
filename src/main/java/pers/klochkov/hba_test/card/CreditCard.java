package pers.klochkov.hba_test.card;

import java.math.BigDecimal;

public class CreditCard extends BankCard {
    private BigDecimal creditLimit;
    public CreditCard(BigDecimal balance, BigDecimal creditLimit) {
        super(balance);
        this.creditLimit = creditLimit;
    }

    @Override
    public BigDecimal topUp(BigDecimal amount) {
        balance = balance.add(amount);
        return balance;
    }

    @Override
    public Boolean pay(BigDecimal amount) {
        if (balance.add(creditLimit).compareTo(amount) >= 0) {
             balance = balance.subtract(amount);
             return true;
        }
        return false;
    }

    @Override
    public BigDecimal getAvailableFundsInfo() {
        return balance.add(creditLimit).setScale(2, BigDecimal.ROUND_UP);
    }
}
