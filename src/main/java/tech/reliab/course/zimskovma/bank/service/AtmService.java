package tech.reliab.course.zimskovma.bank.service;

import java.util.List;

import tech.reliab.course.zimskovma.bank.entity.BankAtm;

public interface AtmService {
    BankAtm create(BankAtm bankAtm);

    public BankAtm getBankAtmById(int id);

    public List<BankAtm> getAllBankAtms();

}