package pers.klochkov.hba_test.decorator;

import pers.klochkov.hba_test.card.BankCardOperations;

import java.math.BigDecimal;

public class BankCardAccumulationTopUp extends BankCardDecorator {

private BigDecimal percentForBalanceWhenTopUp;
    public BankCardAccumulationTopUp(BankCardOperations bankCardOperations, BigDecimal percentForBalanceWhenTopUp) {
        super(bankCardOperations);
        this.percentForBalanceWhenTopUp = percentForBalanceWhenTopUp;
    }

    @Override
    public BigDecimal topUp(BigDecimal amount) {
        BigDecimal accumulation = percentForBalanceWhenTopUp.multiply(amount).divide(BigDecimal.valueOf(100));
        super.setBalance(super.getBalance().add(accumulation));
        return super.topUp(amount).setScale(2, BigDecimal.ROUND_UP);
    }
}
