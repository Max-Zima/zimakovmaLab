package tech.reliab.course.zimskovma.bank.entity;

import java.math.BigDecimal;
import java.util.UUID;

public class PaymentAccount extends Account {
    private BigDecimal balance;

    private void initDefault() {
        balance = new BigDecimal("0");
    }

    public PaymentAccount() {
        super();
        initDefault();
    }

    public PaymentAccount(UUID id, User user) {
        super(id, user);
        initDefault();
    }

    public PaymentAccount(User user, Bank bank) {
        super(user, bank);
        initDefault();
    }

    public PaymentAccount(UUID id, User user, Bank bank) {
        super(id, user, bank);
        initDefault();
    }

    public PaymentAccount(User user, Bank bank, BigDecimal balance) {
        super(user, bank);
        this.balance = balance;
    }

    public PaymentAccount(UUID id, User user, Bank bank, BigDecimal balance) {
        super(id, user, bank);
        this.balance = balance;
    }

    public PaymentAccount(PaymentAccount paymentAccount) {
        super(paymentAccount.id, paymentAccount.user, paymentAccount.bank);
        this.balance = paymentAccount.balance;
    }

    public BigDecimal getBalance() {
        return this.balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "{" +
                "\n account='" + super.toString() + "'" +
                ",\n balance='" + getBalance() + "'" +
                "\n}";
    }
}
