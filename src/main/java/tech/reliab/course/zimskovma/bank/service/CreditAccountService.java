package tech.reliab.course.zimskovma.bank.service;

import tech.reliab.course.zimskovma.bank.entity.CreditAccount;

import java.util.List;

public interface CreditAccountService {
    CreditAccount create(CreditAccount creditAccount);

    public CreditAccount getCreditAccountById(int id);

    public List<CreditAccount> getAllCreditAccounts();
}
