package tech.reliab.course.zimskovma.bank.service;

import java.util.List;

import tech.reliab.course.zimskovma.bank.entity.BankAtm;
import tech.reliab.course.zimskovma.bank.entity.BankOffice;
import tech.reliab.course.zimskovma.bank.entity.Employee;

public interface BankOfficeService {
    BankOffice create(BankOffice bankOffice);

    public void printBankOfficeData(int id);

    public BankOffice getBankOfficeById(int id);

    public List<BankOffice> getAllOffices();

    void setEmployeeService(EmployeeService employeeService);

    void setAtmService(AtmService atmService);

    public List<Employee> getAllEmployeesByOfficeId(int id);

    boolean addMoney(BankOffice bankOffice, double amount);

    boolean addAtm(int id, BankAtm bankAtm);

    boolean addEmployee(int id, Employee employee);

    List<BankAtm> getSuitableBankAtmInOffice(BankOffice bankOffice, double money);

    List<Employee> getSuitableEmployeeInOffice(BankOffice bankOffice);

    boolean isSuitableBankOffice(BankOffice bankOffice, double money);
}
