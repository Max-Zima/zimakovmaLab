package tech.reliab.course.zimskovma.bank.entity;

import java.time.LocalDate;
import java.math.BigDecimal;
import java.util.UUID;

public class Employee {
    public enum Job {
        Manager,
        Worker
    }

    private UUID id;
    private String name;
    private LocalDate birthDate;
    private Job job;
    private Bank bank;
    private boolean isWorkingFromHome;
    private BankOffice bankOffice;
    private boolean isCreditAvailable;
    private double  salary;

    private void initDefault() {
        id = UUID.randomUUID();
        name = "No name";
        birthDate = null;
        job = null;
        bank = null;
        isWorkingFromHome = false;
        bankOffice = null;
        isCreditAvailable = false;
        salary = 0;
    }

    public Employee() {
        initDefault();
    }

    public Employee(String name) {
        initDefault();
        this.name = name;
    }

    public Employee(String name, LocalDate birthDate) {
        initDefault();
        this.name = name;
        this.birthDate = birthDate;
    }

    public Employee(String name, Bank bank, BankOffice bankOffice) {
        initDefault();
        this.name = name;
        this.bank = bank;
        this.bankOffice = bankOffice;
    }

    public Employee(String name, LocalDate birthDate, Job job, Bank bank, boolean isWorkingFromHome,
                    BankOffice bankOffice, boolean isCreditAvailable, double salary) {
        initDefault();
        this.name = name;
        this.birthDate = birthDate;
        this.job = job;
        this.bank = bank;
        this.isWorkingFromHome = isWorkingFromHome;
        this.bankOffice = bankOffice;
        this.isCreditAvailable = isCreditAvailable;
        this.salary = salary;
    }

    public Employee(UUID id, String name, LocalDate birthDate, Job job, Bank bank, boolean isWorkingFromHome,
                    BankOffice bankOffice, boolean isCreditAvailable, double salary) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
        this.job = job;
        this.bank = bank;
        this.isWorkingFromHome = isWorkingFromHome;
        this.bankOffice = bankOffice;
        this.isCreditAvailable = isCreditAvailable;
        this.salary = salary;
    }

    public Employee(Employee employee) {
        this.id = employee.id;
        this.name = employee.name;
        this.birthDate = employee.birthDate;
        this.job = employee.job;
        this.bank = new Bank(employee.bank);
        this.isWorkingFromHome = employee.isWorkingFromHome;
        this.bankOffice = new BankOffice(employee.bankOffice);
        this.isCreditAvailable = employee.isCreditAvailable;
        this.salary = employee.salary;
    }

    public UUID getId() {
        return this.id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthDate() {
        return this.birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Job getJob() {
        return this.job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public Bank getBank() {
        return this.bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public boolean isIsWorkingFromHome() {
        return this.isWorkingFromHome;
    }

    public boolean getIsWorkingFromHome() {
        return this.isWorkingFromHome;
    }

    public void setIsWorkingFromHome(boolean isWorkingFromHome) {
        this.isWorkingFromHome = isWorkingFromHome;
    }

    public BankOffice getBankOffice() {
        return this.bankOffice;
    }

    public void setBankOffice(BankOffice bankOffice) {
        this.bankOffice = bankOffice;
    }

    public boolean isIsCreditAvailable() {
        return this.isCreditAvailable;
    }

    public boolean getIsCreditAvailable() {
        return this.isCreditAvailable;
    }

    public void setIsCreditAvailable(boolean isCreditAvailable) {
        this.isCreditAvailable = isCreditAvailable;
    }

    public double  getSalary() {
        return this.salary;
    }

    public void setSalary(double  salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Сотрудник банка " + this.bank.getName()  +
                "\n ФИО ='" + getName() + "'" +
                ",\n Дата рождения ='" + getBirthDate() + "'" +
                ",\n Должность ='" + getJob() + "'" +
                ",\n Работает ли в банковском офисе или удаленно? ='" + isIsWorkingFromHome() + "'" +
                ",\n Банковский офис, в котором работает ='" + this.bankOffice.getName() + "'" +
                ",\n Может ли выдавать кредиты?='" + isIsCreditAvailable() + "'" +
                ",\n Размер зарплаты ='" + String.format("%.2f", getSalary()) + "'" + "\n";
    }
}
