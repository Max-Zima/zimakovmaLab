package tech.reliab.course.zimskovma.bank.service;

import java.math.BigDecimal;

import tech.reliab.course.zimskovma.bank.entity.PaymentAccount;

public interface PaymentAccountService {
    PaymentAccount create(PaymentAccount paymentAccount);

    boolean depositMoney(PaymentAccount paymentAccount, double amount);

    boolean withdrawMoney(PaymentAccount paymentAccount, double amount);
}