package pers.klochkov.hba_test.card;

import java.math.BigDecimal;

public class DebitCard extends BankCard {
    public DebitCard(BigDecimal balance) {
        super(balance);
    }

    @Override
    public BigDecimal topUp(BigDecimal amount) {
        balance = balance.add(amount);
        return balance;
    }


    @Override
    public Boolean pay(BigDecimal amount) {
        if (balance.compareTo(amount) >= 0) {
            balance = balance.subtract(amount);
            return true;
        }
        return false;
    }

    @Override
    public BigDecimal getAvailableFundsInfo() {
        return balance.setScale(2);
    }
}
