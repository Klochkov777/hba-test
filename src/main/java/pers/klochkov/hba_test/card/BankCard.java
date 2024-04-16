package pers.klochkov.hba_test.card;

import java.math.BigDecimal;

public abstract class BankCard implements BankCardOperations {
    protected BigDecimal balance;

    public BankCard(BigDecimal balance) {
        this.balance = balance;
    }

    @Override
    public BigDecimal getBalanceInfo() {
        return balance.setScale(2, BigDecimal.ROUND_UP);
    }
    @Override
    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    @Override
    public BigDecimal getBalance() {
        return this.balance.setScale(2,BigDecimal.ROUND_UP);
    }
}
