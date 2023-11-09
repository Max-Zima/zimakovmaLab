package tech.reliab.course.zimskovma.bank.entity;

import java.util.UUID;

public class Account {
    private static int currentId = 0;

    protected int id;
    protected User user;
    protected Bank bank;

    private void initializeId() {
        this.id = currentId++;
    }

    private void initDefault() {
        initializeId();
        user = null;
        bank = null;
    }

    public Account() {
        initializeId();
        initDefault();
    }

    public Account(User user) {
        this.user = user;
    }

    public Account(int id, User user) {
        this.id = currentId++;
        this.user = user;
    }

    public Account(int id, User user, Bank bank) {
        this.id = id;
        this.user = user;
        this.bank = bank;
    }

    public Account(User user, Bank bank) {
        initializeId();
        initDefault();
        this.user = user;
        this.bank = bank;
    }

    public Account(Account account) {
        this.id = account.id;
        this.user = new User(account.user);
        this.bank = new Bank(account.bank);
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getClient() {
        return this.user;
    }

    public void setUser(User client) {
        this.user = client;
    }

    public Bank getBank() {
        return this.bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    @Override
    public String toString() {
        return "аккаунт" +
                "\n id аккаунта = " + getId() +
                ",\n Имя клиента = " + this.user.getName() +
                ",\n Банк = " + this.bank.getName() + ",\n";
    }
}
