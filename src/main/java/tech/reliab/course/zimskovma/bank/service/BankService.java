package tech.reliab.course.zimskovma.bank.service;

import tech.reliab.course.zimskovma.bank.entity.*;
import tech.reliab.course.zimskovma.bank.exception.CreditException;
import tech.reliab.course.zimskovma.bank.exception.IncomException;
import tech.reliab.course.zimskovma.bank.exception.RatingException;

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

    boolean addEmployee(Bank bank, Employee employee);

    List<BankOffice> getBankOfficeSuitableInBank(Bank bank, double money);

    boolean isBankSuitable(Bank bank, double money);

    List<Bank> getBanksSuitable(double sum, int countMonth) throws CreditException;

    boolean approveCredit(Bank bank, CreditAccount account, Employee employee) throws CreditException, RatingException, IncomException;

    boolean transferClient(User user, int newBankId);
}
