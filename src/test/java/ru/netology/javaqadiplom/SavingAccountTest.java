package ru.netology.javaqadiplom;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SavingAccountTest {


    //тест c отрицательным балансом
    @Test
    public void negativeBalance() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new SavingAccount(-2_000, 1_000, 10_000, -15);
        }, "Баланс не может быть отрицательным, а у вас:  -2000");
    }

    //тест c отрицательным min балансом
    @Test
    public void negativeMinBalance() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new SavingAccount(2_000, -1_000, 10_000, -15);
        }, "Минимальный баланс не может быть отрицательным, а у вас:  -1000");
    }

    //тест c отрицательным max балансом
    @Test
    public void negativeMaxBalance() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new SavingAccount(2_000, 1_000, -10_000, -15);
        }, "Максимальный баланс не может быть отрицательным, а у вас:  -10000");
    }

    //тест c минусовой ставкой
    @Test
    public void negativeRate() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new SavingAccount(2_000, 1_000, 10_000, -15);
        }, "Накопительная ставка не может быть отрицательной, а у вас: -15");
    }


    //в пределах допустимого установленного лимита баланса
    @Test
    public void shouldAddLessThanMaxBalance() {
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        account.add(3_000);

        Assertions.assertEquals(2_000 + 3_000, account.getBalance());
    }

    //превышение установленного максимального лимита баланса
    @Test
    public void shouldAddLessThanMinBalance() {
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        account.add(10_000);

        Assertions.assertEquals(2_000 + 0, account.getBalance());
    }

    //пополнение в пределе максимального лимита баланса
    @Test
    public void shouldAddLessThanMaxBalance2() {
        SavingAccount account = new SavingAccount(
                0,
                1_000,
                10_000,
                5
        );

        account.add(10_000);

        Assertions.assertEquals(10_000 + 0, account.getMaxBalance());
    }

    //пополнение баланса на 0
    @Test
    public void shouldAddABalanceOfZero() {
        SavingAccount account = new SavingAccount(
                1_000,
                1_000,
                10_000,
                5
        );

        account.add(0);

        Assertions.assertEquals(1_000, account.getMinBalance());
    }


    //сумма покупки в допустимом пределе баланса
    @Test
    public void addWriteOffIfBalanceIsAcceptable() {
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        account.pay(1000);

        Assertions.assertEquals(2_000 - 1_000, account.getBalance());
    }

    //сумма покупки превышает баланс, оставить текущий баланс
    @Test
    public void addAWriteOffIfTheBalanceIsExceeded() {
        SavingAccount account = new SavingAccount(
                2_000,
                0,
                10_000,
                5
        );

        account.pay(5000);

        Assertions.assertEquals(2_000 - 0, account.getBalance());
    }

    //сумма покупки равна 0
    @Test
    public void doNotWriteOffIfZero() {
        SavingAccount account = new SavingAccount(
                2_000,
                0,
                10_000,
                5
        );

        account.pay(0);

        Assertions.assertEquals(2_000, account.getBalance());
    }

    //расчет процента на остаток если на балансе есть денежные средства
    @Test
    public void interestOnBalance() {
        SavingAccount account = new SavingAccount(
                200,
                0,
                10_000,
                15
        );

        int yearChange = account.yearChange();

        Assertions.assertEquals(30, yearChange);
    }

    //расчет процента на остаток если баланс ноль
    @Test
    public void zeroInterestOnBalance() {
        SavingAccount account = new SavingAccount(
                0,
                0,
                10_000,
                15
        );

        int yearChange = account.yearChange();

        Assertions.assertEquals(0, yearChange);
    }
}