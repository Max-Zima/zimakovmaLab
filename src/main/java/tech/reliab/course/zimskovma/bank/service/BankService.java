package tech.reliab.course.zimskovma.bank.service;

import tech.reliab.course.zimskovma.bank.entity.Bank;
import tech.reliab.course.zimskovma.bank.entity.BankOffice;
import tech.reliab.course.zimskovma.bank.entity.User;

import java.util.List;


public interface BankService {
    Bank create(Bank bank);

    public void setBankOfficeService(BankOfficeService bankOfficeService);

    public void setClientService(UserService userService);

    public Bank getBankById(int bankId);

    public List<Bank> getAllBanks();

    public List<BankOffice> getAllOfficesByBankId(int id);

    public void printBankData(int bankId);

    double calculateInterestRate(Bank bank);

    boolean addOffice(int bankId, BankOffice bankOffice);

    boolean addUser(int id, User user);
}
