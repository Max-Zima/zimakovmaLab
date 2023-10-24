package tech.reliab.course.zimskovma.bank.service;

import tech.reliab.course.zimskovma.bank.entity.Bank;


public interface BankService {
    // Создание банка
    Bank create(Bank bank);

    // Расчет процентной ставки банка
    double calculateInterestRate(Bank bank);
}
