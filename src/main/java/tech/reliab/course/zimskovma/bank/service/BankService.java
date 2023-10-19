package tech.reliab.course.zimskovma.bank.service;

import java.math.BigDecimal;

import tech.reliab.course.zimskovma.bank.entity.Bank;


public interface BankService {
    // Создание банка
    Bank create(Bank bank);

    // Расчет процентной ставки банка
    BigDecimal calculateInterestRate(Bank bank);
}
