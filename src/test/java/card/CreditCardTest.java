package card;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import pers.klochkov.hba_test.card.CreditCard;

import java.math.BigDecimal;

public class CreditCardTest {
    private CreditCard creditCard;

    @BeforeEach
    public void createCreditCard() {
        creditCard = new CreditCard(BigDecimal.valueOf(10000), BigDecimal.valueOf(10000));
    }

    @ParameterizedTest
    @CsvSource({
            "true, 19000, -9000.00, 1000.00",
            "false, 21000, 10000.00, 20000.00"
    })
    public void pay(Boolean expectedBooleanPay, BigDecimal amountPay, BigDecimal expectedBalance, BigDecimal expectedAvailableFunds) {
        Boolean actualBooleanPay = creditCard.pay(amountPay);
        Assertions.assertEquals(expectedBooleanPay, actualBooleanPay);
        Assertions.assertEquals(expectedBalance, creditCard.getBalance());
        Assertions.assertEquals(expectedAvailableFunds, creditCard.getAvailableFundsInfo());
    }

    @Test
    public void topUp() {
        creditCard = new CreditCard(BigDecimal.valueOf(10000), BigDecimal.valueOf(10000));
        creditCard.topUp(BigDecimal.valueOf(2000.05));
        BigDecimal expectedBalance1 = BigDecimal.valueOf(12000.05);
        BigDecimal expectedAvailableFunds1 = BigDecimal.valueOf(22000.05);
        Assertions.assertEquals(expectedBalance1, creditCard.getBalance());
        Assertions.assertEquals(expectedAvailableFunds1, creditCard.getAvailableFundsInfo());

        creditCard = new CreditCard(BigDecimal.valueOf(-5000), BigDecimal.valueOf(10000));
        creditCard.topUp(BigDecimal.valueOf(2000.05));
        BigDecimal expectedBalance2 = BigDecimal.valueOf(-2999.95);
        BigDecimal expectedAvailableFunds2 = BigDecimal.valueOf(7000.05);
        Assertions.assertEquals(expectedBalance2, creditCard.getBalance());
        Assertions.assertEquals(expectedAvailableFunds2, creditCard.getAvailableFundsInfo());
    }

    @Test
    public void getAvailableFundsInfo() {
        Assertions.assertEquals(BigDecimal.valueOf(20000.00).setScale(2), creditCard.getAvailableFundsInfo());
    }
}
