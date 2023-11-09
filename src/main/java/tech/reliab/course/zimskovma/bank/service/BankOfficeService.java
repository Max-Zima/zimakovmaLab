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

    public List<Employee> getAllEmployeesByOfficeId(int id);

    boolean addAtm(int id, BankAtm bankAtm);

    boolean addEmployee(int id, Employee employee);
}
