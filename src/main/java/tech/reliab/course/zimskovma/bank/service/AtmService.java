package tech.reliab.course.zimskovma.bank.service;

import java.util.List;

import tech.reliab.course.zimskovma.bank.entity.BankAtm;

public interface AtmService {
    BankAtm create(BankAtm bankAtm);

    public BankAtm getBankAtmById(int id);

    public List<BankAtm> getAllBankAtms();

    boolean isAtmSuitable(BankAtm bankAtm, double money);

    boolean addMoney(BankAtm bankAtm, double amount);
}