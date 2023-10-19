package tech.reliab.course.zimskovma.bank.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class User {
    public static final BigDecimal MAX_MONTHLY_INCOME = new BigDecimal("10000");

    private UUID id;
    private String name;
    private LocalDate birthDate;
    private String placeOfWork;
    private BigDecimal monthlyIncome;
    private Bank bank;
    private BigDecimal creditRating;


    private void initDefault() {
        id = UUID.randomUUID();
        name = "No name";
        birthDate = null;
        placeOfWork = "No place of work";
        monthlyIncome = new BigDecimal("0");
        bank = null;
        creditRating = new BigDecimal("0");
    }

    public User() {
        initDefault();
    }

    public User(String name) {
        initDefault();
        this.name = name;
    }

    public User(String name, LocalDate birthDate) {
        initDefault();
        this.name = name;
        this.birthDate = birthDate;
    }

    public User(String name, Bank bank) {
        initDefault();
        this.name = name;
        this.bank = bank;
    }

    public User(String name, LocalDate birthDate, String placeOfWork, BigDecimal monthlyIncome, Bank bank,
                BigDecimal creditRating) {
        initDefault();
        this.name = name;
        this.birthDate = birthDate;
        this.placeOfWork = placeOfWork;
        this.monthlyIncome = monthlyIncome;
        this.bank = bank;
        this.creditRating = creditRating;
    }

    public User(UUID id, String name, LocalDate birthDate, String placeOfWork, BigDecimal monthlyIncome, Bank bank,
                BigDecimal creditRating) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
        this.placeOfWork = placeOfWork;
        this.monthlyIncome = monthlyIncome;
        this.bank = bank;
        this.creditRating = creditRating;
    }

    public User(User user) {
        this.id = user.id;
        this.name = user.name;
        this.birthDate = user.birthDate;
        this.placeOfWork = user.placeOfWork;
        this.monthlyIncome = user.monthlyIncome;
        this.bank = new Bank(user.bank);
        this.creditRating = user.creditRating;
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

    public String getPlaceOfWork() {
        return this.placeOfWork;
    }

    public void setPlaceOfWork(String placeOfWork) {
        this.placeOfWork = placeOfWork;
    }

    public BigDecimal getMonthlyIncome() {
        return this.monthlyIncome;
    }

    public void setMonthlyIncome(BigDecimal monthlyIncome) {
        this.monthlyIncome = monthlyIncome;
    }

    public Bank getBank() {
        return this.bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public BigDecimal getCreditRating() {
        return this.creditRating;
    }

    public void setCreditRating(BigDecimal creditRating) {
        this.creditRating = creditRating;
    }

    @Override
    public String toString() {
        return "{" +
                "\n id='" + getId() + "'" +
                ",\n name='" + getName() + "'" +
                ",\n birthdDate='" + getBirthDate() + "'" +
                ",\n person='" + super.toString() + "'" +
                ",\n placeOfWork='" + getPlaceOfWork() + "'" +
                ",\n monthlyIncome='" + getMonthlyIncome() + "'" +
                ",\n bank='" + getBank() + "'" +
                ",\n creditRating='" + getCreditRating() + "'" +
                "\n}";
    }
}
