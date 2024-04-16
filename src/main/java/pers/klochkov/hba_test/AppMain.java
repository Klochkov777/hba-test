package pers.klochkov.hba_test;

import pers.klochkov.hba_test.card.BankCardOperations;
import pers.klochkov.hba_test.card.CreditCard;
import pers.klochkov.hba_test.card.DebitCard;
import pers.klochkov.hba_test.decorator.BankCardAccumulationTopUp;
import pers.klochkov.hba_test.decorator.BankCardBonus;
import pers.klochkov.hba_test.decorator.BankCardCashbackPercent;

import java.math.BigDecimal;

public class AppMain {
    public static void main(String[] args) {

        // проверка методов при не использовании дополнитеьных программ

        //проверка при оплате;
        //1. DebitCard
        DebitCard debitCard = new DebitCard(BigDecimal.valueOf(10000));

        System.out.println(String.format("1.1 При оплате debitCard expectMeanOfPay = true, actualBalance = %s", debitCard.pay(BigDecimal.valueOf(2000))));
        System.out.println(String.format("1.2 После оплаты debitCard expectBalance = 8000.00, actualBalance = %s", debitCard.getBalance()));

        System.out.println("\nПосле еще одной попытки оплаты на сумму больше чем баланс");
        System.out.println(String.format("1.3 При оплате debitCard expectMeanOfPay = false, actualBalance = %s", debitCard.pay(BigDecimal.valueOf(16000))));
        System.out.println(String.format("1.4 После оплаты debitCard expectBalance = 8000.00, actualBalance = %s, то есть не изменился", debitCard.getBalance()));

        //2. CreditCard
        CreditCard creditCard = new CreditCard(BigDecimal.valueOf(10000), BigDecimal.valueOf(5000));

        System.out.println("\n\n\nПроверка оплаты creditCard");
        System.out.println(String.format("2.1 При оплате creditCard болше чем доступных средств expectMeanOfPay = false, actualBalance = %s", creditCard.pay(BigDecimal.valueOf(20000))));
        System.out.println(String.format("2.2 После оплаты creditCard expectBalance = 10000.00, actualBalance = %s", creditCard.getBalance()));
        System.out.println(String.format("2.3 После оплаты creditCard expectedAvailableFundsInfo = 15000.00, actualBalance = %s", creditCard.getAvailableFundsInfo()));

        System.out.println(String.format("\n2.4 При оплате creditCard меньше чем доступных средств expectMeanOfPay = false, actualBalance = %s", creditCard.pay(BigDecimal.valueOf(13000))));
        System.out.println(String.format("2.5 После оплаты creditCard expectBalance = -3000, actualBalance = %s", creditCard.getBalance()));
        System.out.println(String.format("2.6 После оплаты creditCard expectedAvailableFundsInfo = 2000, actualBalance = %s", creditCard.getAvailableFundsInfo()));

        //проверка при пополнении
        //3 DebitCard
        System.out.println("\n\n\nПроверка пополнения DebitCard");
        debitCard = new DebitCard(BigDecimal.valueOf(0));

        debitCard.topUp(BigDecimal.valueOf(5000));
        System.out.println(String.format("3.1После пополнения на 5000 новой expectedBalance = 5000.00, actualBalance = %s, expectedAvailableFunds = 5000.00, actualAvailableFunds = %s", debitCard.getBalance(), debitCard.getAvailableFundsInfo()));
        debitCard.topUp(BigDecimal.valueOf(2000));
        System.out.println(String.format("3.2После пополнения еще на 2000 expectedBalance = 7000.00, actualBalance = %s, expectedAvailableFunds = 7000.00, actualAvailableFunds = %s", debitCard.getBalance(), debitCard.getAvailableFundsInfo()));


        //проверка при пополнении
        //4 CreditCard
        System.out.println("\n\n\n4Проверка пополнения CreditCard");
        creditCard = new CreditCard(BigDecimal.valueOf(5000), BigDecimal.valueOf(5000));

        creditCard.topUp(BigDecimal.valueOf(5000));
        System.out.println(String.format("4.1После пополнения на 5000 новой expectedBalance = 10000.00, actualBalance = %s, expectedAvailableFunds = 15000.00, actualAvailableFunds = %s", creditCard.getBalance(), creditCard.getAvailableFundsInfo()));
        creditCard.pay(BigDecimal.valueOf(12000));
        creditCard.topUp(BigDecimal.valueOf(1000));
        System.out.println(String.format("4.2После платежа в 12000 пополнения еще на 1000 expectedBalance = -1000.00, actualBalance = %s, expectedAvailableFunds = 4000.00, actualAvailableFunds = %s", creditCard.getBalance(), creditCard.getAvailableFundsInfo()));


        //проверка bankCardAccumulationTopUp при пополнении
        //5 bankCardAccumulationTopUp
        System.out.println("\n\n\n5Проверка пополнения bankCardAccumulationTopUp");
        BankCardOperations bankCard = new CreditCard(BigDecimal.valueOf(5000), BigDecimal.valueOf(5000));
        bankCard = new BankCardAccumulationTopUp(bankCard, BigDecimal.valueOf(0.005));

        bankCard.topUp(BigDecimal.valueOf(100000));
        System.out.println(String.format("5.1После пополнения на 100_000 новой expectedBalance = 105005.00, actualBalance = %s, expectedAvailableFunds = 110005.00, actualAvailableFunds = %s", bankCard.getBalance(), bankCard.getAvailableFundsInfo()));


        //проверка с кэшбэком, при чем возврат кэшбэка навешиваем на ту же карту что и подключена к на 0.005% пополнения которая использовалась в предыдущем примере
        System.out.println("\n\n\n6Проверка при платеже BankCardCashbackPercent");
        bankCard = new BankCardCashbackPercent(bankCard, BigDecimal.valueOf(5), BigDecimal.valueOf(5000));
        bankCard.pay(BigDecimal.valueOf(10000));
        System.out.println(String.format("6.1После платежа на 10_000 новой expectedBalance = 95505.00, actualBalance = %s, expectedAvailableFunds = 100505.00, actualAvailableFunds = %s", bankCard.getBalance(), bankCard.getAvailableFundsInfo()));
        bankCard.pay(BigDecimal.valueOf(1000));
        System.out.println(String.format("6.1После платежа на 1000 баланс уменьшился на 1000 но кэшбэк не вернулся, тк условие если платеж больше 5000 новой expectedBalance = 94505.00, actualBalance = %s, expectedAvailableFunds = 99505.00, actualAvailableFunds = %s", bankCard.getBalance(), bankCard.getAvailableFundsInfo()));


        //проверка что у карты есть бонусы, при чем на ту же карту у которой имеютя все остальные бонусные программы
        System.out.println("\n\n\n7Проверка при платеже и появление бонусов в 1%");
        bankCard = new BankCardBonus(bankCard, BigDecimal.valueOf(1));
        bankCard.pay(BigDecimal.valueOf(10000));
        System.out.println(String.format("7.1После платежа на 10_000 новой expectedBalance = 85005.00, actualBalance = %s, expectedAvailableFunds = 90005.00, actualAvailableFunds = %s", bankCard.getBalance(), bankCard.getAvailableFundsInfo()));
        System.out.println(String.format("Проверяем бонусы, expectedBonusPont = 100, actualBonusPoint = %s", ((BankCardBonus) bankCard).getBonusPoints()));
    }
}
