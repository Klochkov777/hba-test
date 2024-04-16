package card;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import pers.klochkov.hba_test.card.DebitCard;

import java.math.BigDecimal;


public class DebitCardTest {

    private DebitCard debitCard;

    @BeforeEach
    public void createDebitCard() {
        debitCard = new DebitCard(BigDecimal.valueOf(10000));
    }

    @ParameterizedTest
    @CsvSource({
            "true, 8000, 2000.00",
            "false, 12000, 10000.00"
    })
    public void pay(Boolean expectedBooleanPay, BigDecimal amountPay, BigDecimal expectedBalance) {
        Boolean actualBooleanPay = debitCard.pay(amountPay);
        Assertions.assertEquals(expectedBooleanPay, actualBooleanPay);
        Assertions.assertEquals(expectedBalance, debitCard.getBalance());
    }

    @ParameterizedTest
    @CsvSource({
            "8000, 18000.00",
            "12000, 22000.00"
    })
    public void topUp(BigDecimal amount, BigDecimal expectedBalance) {
        debitCard.topUp(amount);
        Assertions.assertEquals(expectedBalance, debitCard.getBalance());
    }

    @Test
    public void getAvailableFundsInfo() {
        Assertions.assertEquals(BigDecimal.valueOf(10000.00).setScale(2), debitCard.getAvailableFundsInfo());
    }
}
