package tech.reliab.course.zimskovma.bank.service.impl;

import java.math.BigDecimal;
import java.util.*;

import tech.reliab.course.zimskovma.bank.entity.CreditAccount;
import tech.reliab.course.zimskovma.bank.service.CreditAccountService;
import tech.reliab.course.zimskovma.bank.service.UserService;

public class CreditAccountServiceImpl implements CreditAccountService {
    Map<Integer, CreditAccount> creditAccountsTable = new HashMap<Integer, CreditAccount>();
    private final UserService userService;

    public CreditAccountServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public List<CreditAccount> getAllCreditAccounts() {
        return new ArrayList<CreditAccount>(creditAccountsTable.values());
    }

    @Override
    public CreditAccount getCreditAccountById(int id) {
        CreditAccount account = creditAccountsTable.get(id);
        if (account == null) {
            System.err.println("Credit account id =" + id + " is not found");
        }
        return account;
    }

    @Override
    public CreditAccount create(CreditAccount creditAccount) {
        if (creditAccount == null) {
            return null;
        }

        CreditAccount newAccount = new CreditAccount(creditAccount);
        creditAccountsTable.put(newAccount.getId(), newAccount);
        userService.addCreditAccount(newAccount.getClient().getId(), newAccount);

        return newAccount;
    }

}
