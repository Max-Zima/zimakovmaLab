package tech.reliab.course.zimskovma.bank.service.impl;

import java.math.BigDecimal;

import tech.reliab.course.zimskovma.bank.entity.PaymentAccount;
import tech.reliab.course.zimskovma.bank.service.PaymentAccountService;

public class PaymentAccountServiceImpl implements PaymentAccountService {
    @Override
    public PaymentAccount create(PaymentAccount paymentAccount) {
        if (paymentAccount == null) {
            return null;
        }

        if (paymentAccount.getBalance() < 0) {
            System.err.println("Error: PaymentAccount - payment account balance doesn't be negative");
            return null;
        }

        return new PaymentAccount(paymentAccount);
    }

    @Override
    public boolean depositMoney(PaymentAccount paymentAccount, double amount) {
        if (paymentAccount == null) {
            System.err.println("Error: PaymentAccount - non existing payment account");
            return false;
        }

        if (amount <= 0) {
            System.err.println("Error: PaymentAccount - deposit amount doesn't be negative");
            return false;
        }

        paymentAccount.setBalance(paymentAccount.getBalance() + amount);
        return true;
    }

    @Override
    public boolean withdrawMoney(PaymentAccount paymentAccount, double amount) {
        if (paymentAccount == null) {
            System.err.println("Error: PaymentAccount - non existing payment account");
            return false;
        }

        if (amount <= 0) {
            System.err.println("Error: PaymentAccount - withdrawal amount doesn't be negative");
            return false;
        }

        paymentAccount.setBalance(paymentAccount.getBalance() - amount);

        return true;
    }

}
