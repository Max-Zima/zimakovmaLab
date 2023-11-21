package tech.reliab.course.zimskovma.bank.service;

import java.util.List;

import tech.reliab.course.zimskovma.bank.entity.CreditAccount;
import tech.reliab.course.zimskovma.bank.entity.PaymentAccount;
import tech.reliab.course.zimskovma.bank.entity.User;

public interface UserService {
    User create(User user);

    PaymentAccount getBestPaymentAccount(int id);

    public void printUserData(int id, boolean withAccounts);

    public boolean addCreditAccount(int id, CreditAccount account);

    public boolean addPaymentAccount(int id, PaymentAccount account);

    public List<PaymentAccount> getAllPaymentAccountsByClientId(int id);

    public  List<CreditAccount> getAllCreditAccountsByClientId(int id);

    public List<User> getAllUsers();

    public User getUserById(int id);

    double calculateCreditRating(User user);
}
