package pers.klochkov.hba_test.decorator;

import pers.klochkov.hba_test.card.BankCardOperations;

import java.math.BigDecimal;

public class BankCardCashbackPercent extends BankCardDecorator {
    private BigDecimal percentCashback;
    private BigDecimal predicatePaySum;

    public BankCardCashbackPercent(BankCardOperations bankCardOperations, BigDecimal percentCashback, BigDecimal predicatePaySum) {
        super(bankCardOperations);
        this.percentCashback = percentCashback;
        this.predicatePaySum = predicatePaySum;
    }

    @Override
    public Boolean pay(BigDecimal amount) {
        if (amount.compareTo(predicatePaySum) >= 0) {
            BigDecimal cashback =  amount.multiply(percentCashback).divide(BigDecimal.valueOf(100));
            super.setBalance(super.getBalance().add(cashback));
        }
        return super.pay(amount);
    }
}
