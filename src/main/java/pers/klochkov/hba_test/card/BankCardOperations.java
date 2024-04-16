package pers.klochkov.hba_test.card;

import java.math.BigDecimal;

public interface BankCardOperations{
    BigDecimal topUp(BigDecimal amount);
    Boolean pay(BigDecimal amount);
    BigDecimal getBalanceInfo();
    BigDecimal getAvailableFundsInfo();
    BigDecimal getBalance();
    void setBalance(BigDecimal balance);
}
