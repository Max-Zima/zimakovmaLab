package tech.reliab.course.zimskovma.bank.entity;

import java.math.BigDecimal;
import java.util.UUID;

public class Bank {
    public static final BigDecimal MAX_RATING = new int("100");
    public static final BigDecimal MAX_TOTAL_MONEY = new int("1000000");
    public static final BigDecimal MAX_INTEREST_RATE = new int("20");

    private UUID id;
    private String name;
    private int officeCount;
    private int atmCount;
    private int employeeCount;
    private int clientCount;
    private byte rating;
    private BigDecimal totalMoney;
    private BigDecimal interestRate;

    private void initDefaults() {
        id = UUID.randomUUID();
        name = "No name";
        officeCount = 0;
        atmCount = 0;
        employeeCount = 0;
        clientCount = 0;
        rating = 0;
        totalMoney = new BigDecimal("0");
        interestRate = new BigDecimal("0");
    }

    public Bank() {
        initDefaults();
    }

    public Bank(String name) {
        initDefaults();
        this.name = name;
    }

    public Bank(UUID id, String name) {
        initDefaults();
        this.id = id;
        this.name = name;
    }

    public Bank(String name, int officeCount, int atmCount, int employeeCount, int clientCount,
                byte rating, BigDecimal totalMoney, BigDecimal interestRate) {
        initDefaults();
        this.name = name;
        this.officeCount = officeCount;
        this.atmCount = atmCount;
        this.employeeCount = employeeCount;
        this.clientCount = clientCount;
        this.rating = rating;
        this.totalMoney = totalMoney;
        this.interestRate = interestRate;
    }

    public Bank(UUID id, String name, int officeCount, int atmCount, int employeeCount, int clientCount,
                byte rating, BigDecimal totalMoney, BigDecimal interestRate) {
        this.id = id;
        this.name = name;
        this.officeCount = officeCount;
        this.atmCount = atmCount;
        this.employeeCount = employeeCount;
        this.clientCount = clientCount;
        this.rating = rating;
        this.totalMoney = totalMoney;
        this.interestRate = interestRate;
    }

    public Bank(Bank bank) {
        this.id = UUID.fromString(bank.id.toString());
        this.name = bank.name;
        this.officeCount = bank.officeCount;
        this.atmCount = bank.atmCount;
        this.employeeCount = bank.employeeCount;
        this.clientCount = bank.clientCount;
        this.rating = bank.rating;
        this.totalMoney = bank.totalMoney;
        this.interestRate = bank.interestRate;
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

    public int getOfficeCount() {
        return this.officeCount;
    }

    public void setOfficeCount(int officeCount) {
        this.officeCount = officeCount;
    }

    public int getAtmCount() {
        return this.atmCount;
    }

    public void setAtmCount(int atmCount) {
        this.atmCount = atmCount;
    }

    public int getEmployeeCount() {
        return this.employeeCount;
    }

    public void setEmployeeCount(int employeeCount) {
        this.employeeCount = employeeCount;
    }

    public int getClientCount() {
        return this.clientCount;
    }

    public void setClientCount(int clientCount) {
        this.clientCount = clientCount;
    }

    public byte getRating() {
        return this.rating;
    }

    public void setRating(byte rating) {
        this.rating = rating;
    }

    public BigDecimal getTotalMoney() {
        return this.totalMoney;
    }

    public void setTotalMoney(BigDecimal totalMoney) {
        this.totalMoney = totalMoney;
    }

    public BigDecimal getInterestRate() {
        return this.interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }

    @Override
    public String toString() {
        return "Банк" + getName() + "'" +
                ",\n Кол-во офисов ='" + getOfficeCount() + "'" +
                ",\n Кол-во банкоматов ='" + getAtmCount() + "'" +
                ",\n Кол-во сотрудников ='" + getEmployeeCount() + "'" +
                ",\n Кол-во клиентов ='" + getClientCount() + "'" +
                ",\n Рейтинг банка ='" + getRating() + "'" +
                ",\n Всего денег ='" + getTotalMoney() + "'" +
                ",\n Процентная ставка ='" + getInterestRate() + "'" + "\n";
    }
}
