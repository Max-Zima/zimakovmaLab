package tech.reliab.course.zimskovma.bank.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class CreditAccount extends Account{
    private LocalDate dateStart;
    private LocalDate dateEnd;
    private int monthCount;
    private BigDecimal creditAmount;
    private BigDecimal remainingCreditAmount;
    private BigDecimal montlyPayment;
    private BigDecimal interestRate;
    private Employee employee;
    private PaymentAccount paymentAccount;

    private void initDefault() {
        dateStart = null;
        dateEnd = null;
        monthCount = 0;
        creditAmount = new BigDecimal("0");
        remainingCreditAmount = new BigDecimal("0");
        montlyPayment = new BigDecimal("0");
        interestRate = new BigDecimal("0");
        employee = null;
        paymentAccount = null;
    }

    public CreditAccount() {
        super();
        initDefault();
    }

    public CreditAccount(User user) {
        super(user);
        initDefault();
    }
    public CreditAccount(UUID id, User user) {
        super(id, user);
        initDefault();
    }

    public CreditAccount(User user, Bank bank) {
        super(user, bank);
        initDefault();
    }

    public CreditAccount(UUID id, User user, Bank bank) {
        super(id, user, bank);
        initDefault();
    }

    public CreditAccount(User user, Bank bank, Employee employee, PaymentAccount paymentAccount) {
        super(user, bank);
        initDefault();
        this.employee = employee;
        this.paymentAccount = paymentAccount;
    }

    public CreditAccount(User user, Bank bank, LocalDate dateStart, LocalDate dateEnd, int monthCount,
                         BigDecimal creditAmount, BigDecimal remainingCreditAmount, BigDecimal montlyPayment,
                         BigDecimal interestRate, Employee employee, PaymentAccount paymentAccount) {
        super(user, bank);
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.monthCount = monthCount;
        this.creditAmount = creditAmount;
        this.remainingCreditAmount = remainingCreditAmount;
        this.montlyPayment = montlyPayment;
        this.interestRate = interestRate;
        this.employee = employee;
        this.paymentAccount = paymentAccount;
    }

    public CreditAccount(UUID id, User user, Bank bank, LocalDate dateStart, LocalDate dateEnd, int monthCount,
                         BigDecimal creditAmount, BigDecimal remainingCreditAmount, BigDecimal montlyPayment,
                         BigDecimal interestRate, Employee employee, PaymentAccount paymentAccount) {
        super(id, user, bank);
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.monthCount = monthCount;
        this.creditAmount = creditAmount;
        this.remainingCreditAmount = remainingCreditAmount;
        this.montlyPayment = montlyPayment;
        this.interestRate = interestRate;
        this.employee = employee;
        this.paymentAccount = paymentAccount;
    }

    public CreditAccount(CreditAccount creditAccount) {
        super(creditAccount.id, creditAccount.user, creditAccount.bank);
        this.dateStart = creditAccount.dateStart;
        this.dateEnd = creditAccount.dateEnd;
        this.monthCount = creditAccount.monthCount;
        this.creditAmount = creditAccount.creditAmount;
        this.remainingCreditAmount = creditAccount.remainingCreditAmount;
        this.montlyPayment = creditAccount.montlyPayment;
        this.interestRate = creditAccount.interestRate;
        this.employee = new Employee(creditAccount.employee);
        this.paymentAccount = new PaymentAccount(creditAccount.paymentAccount);
    }

    public LocalDate getDateStart() {
        return this.dateStart;
    }

    public void setDateStart(LocalDate dateStart) {
        this.dateStart = dateStart;
    }

    public LocalDate getDateEnd() {
        return this.dateEnd;
    }

    public void setDateEnd(LocalDate dateEnd) {
        this.dateEnd = dateEnd;
    }

    public int getMonthCount() {
        return this.monthCount;
    }

    public void setMonthCount(int monthCount) {
        this.monthCount = monthCount;
    }

    public BigDecimal getCreditAmount() {
        return this.creditAmount;
    }

    public void setCreditAmount(BigDecimal creditAmount) {
        this.creditAmount = creditAmount;
    }

    public BigDecimal getRemainingCreditAmount() {
        return this.remainingCreditAmount;
    }

    public void setRemainingCreditAmount(BigDecimal remainingCreditAmount) {
        this.remainingCreditAmount = remainingCreditAmount;
    }

    public BigDecimal getMonthlyPayment() {
        return this.montlyPayment;
    }

    public void setMontlyPayment(BigDecimal montlyPayment) {
        this.montlyPayment = montlyPayment;
    }

    public BigDecimal getInterestRate() {
        return this.interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }

    public Employee getEmployee() {
        return this.employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public PaymentAccount getPaymentAccount() {
        return this.paymentAccount;
    }

    public void setPaymentAccount(PaymentAccount paymentAccount) {
        this.paymentAccount = paymentAccount;
    }

    @Override
    public String toString() {
        return "{" +
                "\n account='" + super.toString() + "'" +
                ",\n dateStart='" + getDateStart() + "'" +
                ",\n dateEnd='" + getDateEnd() + "'" +
                ",\n monthCount='" + getMonthCount() + "'" +
                ",\n creditAmount='" + getCreditAmount() + "'" +
                ",\n remainingCreditAmount='" + getRemainingCreditAmount() + "'" +
                ",\n montlyPayment='" + getMonthlyPayment() + "'" +
                ",\n interestRate='" + getInterestRate() + "'" +
                ",\n employee='" + getEmployee() + "'" +
                ",\n paymentAccount='" + getPaymentAccount() + "'" +
                "\n}";
    }
}
