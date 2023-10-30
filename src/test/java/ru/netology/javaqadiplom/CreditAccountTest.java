package ru.netology.javaqadiplom;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CreditAccountTest {

    @Test
    public void shouldAddToPositiveBalance() {
        CreditAccount account = new CreditAccount(
                0,
                5_000,
                15
        );

        account.add(3_000);

        Assertions.assertEquals(3_000, account.getBalance());
    }

    @Test
    public void zeroBalanceTest() {
        CreditAccount account = new CreditAccount(
                1_000,
                5_000,
                15
        );

        account.add(0);

        Assertions.assertEquals(1_000, account.getBalance());
    }

    @Test
    public void notAddMinusBalanceTest() {
        CreditAccount account = new CreditAccount(
                1_000,
                5_000,
                15
        );

        account.add(-2_000);

        Assertions.assertEquals(1_000, account.getBalance());
    }

    @Test
    public void minusRateTest() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new CreditAccount(1_000, 5_000, -15);
        });


    }

    @Test
    public void minusCreditLimit() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new CreditAccount(1_000, -5_000, 15);
        });
    }


    @Test
    public void minusInitialBalanceTest() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new CreditAccount(-1_000, 5_000, 15);
        });
    }


    @Test
    public void zeroRate() {
        CreditAccount account = new CreditAccount(
                1_000,
                5_000,
                0
        );

        Assertions.assertEquals(0, account.getRate());
    }

    @Test
    public void zeroInitialBalance() {
        CreditAccount account = new CreditAccount(
                0,
                5_000,
                15
        );

        Assertions.assertEquals(0, account.getBalance());
    }

    @Test
    public void amountMinusTest() {
        CreditAccount account = new CreditAccount(
                1_000,
                5_000,
                15
        );

        account.pay(-3_000);

        Assertions.assertEquals(1_000, account.getBalance());
    }

    @Test
    public void zeroCreditLimit() {
        CreditAccount account = new CreditAccount(
                1_000,
                0,
                15
        );

        Assertions.assertEquals(0, account.getCreditLimit());
    }

    @Test
    public void amountZeroTest() {
        CreditAccount account = new CreditAccount(
                1_000,
                5_000,
                15
        );

        account.pay(0);

        Assertions.assertEquals(1_000, account.getBalance());
    }

    @Test
    public void amountPlusTest() {
        CreditAccount account = new CreditAccount(
                1_000,
                5_000,
                15
        );

        account.pay(500);

        Assertions.assertEquals(500, account.getBalance());
    }

    @Test
    public void shouldZeroIfAmountEqualBalance() {
        CreditAccount account = new CreditAccount(
                1_000,
                5_000,
                15
        );

        account.pay(1_000);

        Assertions.assertEquals(0, account.getBalance());
    }

    @Test
    public void shouldBeMinusBalance() {
        CreditAccount account = new CreditAccount(
                1_000,
                5_000,
                15
        );

        account.pay(2_000);

        Assertions.assertEquals(-1_000, account.getBalance());
    }

    @Test
    public void ifAmountIsCreditLimitPlusBalance() {
        CreditAccount account = new CreditAccount(
                1_000,
                5_000,
                15
        );

        account.pay(6_000);

        Assertions.assertEquals(-5_000, account.getBalance());
    }

    @Test
    public void ifAmountIsMoreThanCreditLimitPlusBalance() {
        CreditAccount account = new CreditAccount(
                1_000,
                5_000,
                15
        );

        account.pay(10_000);

        Assertions.assertEquals(1_000, account.getBalance());
    }

    @Test
    public void changeYearTestPlus() {
        CreditAccount account = new CreditAccount(
                1_000,
                5_000,
                15
        );

        account.pay(500);

        Assertions.assertEquals(0, account.yearChange());
    }

    @Test
    public void changeYearTestZero() {
        CreditAccount account = new CreditAccount(
                1_000,
                5_000,
                15
        );

        account.pay(1_000);

        Assertions.assertEquals(0, account.yearChange());
    }

    @Test
    public void changeYearTestMinus() {
        CreditAccount account = new CreditAccount(
                1_000,
                5_000,
                15
        );

        account.pay(3_000);

        Assertions.assertEquals(-300, account.yearChange());
    }
}
