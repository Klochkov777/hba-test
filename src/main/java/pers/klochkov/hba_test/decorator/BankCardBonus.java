package pers.klochkov.hba_test.decorator;

import pers.klochkov.hba_test.card.BankCardOperations;

import java.math.BigDecimal;

public class BankCardBonus extends BankCardDecorator {

    private BigDecimal bonusPoints;
    private BigDecimal percentForBonus;

    public BankCardBonus(BankCardOperations bankCardOperations, BigDecimal percentForBonus) {
        super(bankCardOperations);
        this.percentForBonus = percentForBonus;
    }

    @Override
    public Boolean pay(BigDecimal amount) {
        bonusPoints = amount.multiply(percentForBonus).divide(BigDecimal.valueOf(100));
        return super.pay(amount);
    }

    public BigDecimal getBonusPoints() {
        return bonusPoints;
    }
}
