package pers.klochkov.hba_test.decorator;

import pers.klochkov.hba_test.card.BankCardOperations;

import java.math.BigDecimal;

public class BankCardDecorator implements BankCardOperations {
    private BankCardOperations bankCardOperations;

    public BankCardDecorator(BankCardOperations bankCardOperations) {
        this.bankCardOperations = bankCardOperations;
    }

    @Override
    public BigDecimal topUp(BigDecimal amount) {
        return bankCardOperations.topUp(amount);
    }

    @Override
    public Boolean pay(BigDecimal amount) {
        return bankCardOperations.pay(amount);
    }

    @Override
    public BigDecimal getBalanceInfo() {
        return bankCardOperations.getBalanceInfo();
    }

    @Override
    public BigDecimal getAvailableFundsInfo() {
        return bankCardOperations.getAvailableFundsInfo();
    }

    @Override
    public BigDecimal getBalance() {
        return this.bankCardOperations.getBalance();
    }

    @Override
    public void setBalance(BigDecimal balance) {
        this.bankCardOperations.setBalance(balance);
    }
}
