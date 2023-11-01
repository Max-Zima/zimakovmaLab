package tech.reliab.course.zimskovma.bank;

import java.math.BigDecimal;
import java.time.LocalDate;

import tech.reliab.course.zimskovma.bank.entity.Bank;
import tech.reliab.course.zimskovma.bank.entity.BankAtm;
import tech.reliab.course.zimskovma.bank.entity.BankOffice;
import tech.reliab.course.zimskovma.bank.entity.User;
import tech.reliab.course.zimskovma.bank.entity.CreditAccount;
import tech.reliab.course.zimskovma.bank.entity.Employee;
import tech.reliab.course.zimskovma.bank.entity.PaymentAccount;
import tech.reliab.course.zimskovma.bank.service.AtmService;
import tech.reliab.course.zimskovma.bank.service.BankOfficeService;
import tech.reliab.course.zimskovma.bank.service.BankService;
import tech.reliab.course.zimskovma.bank.service.CreditAccountService;
import tech.reliab.course.zimskovma.bank.service.EmployeeService;
import tech.reliab.course.zimskovma.bank.service.UserService;
import tech.reliab.course.zimskovma.bank.service.PaymentAccountService;
import tech.reliab.course.zimskovma.bank.service.impl.AtmServiceImpl;
import tech.reliab.course.zimskovma.bank.service.impl.BankOfficeServiceImpl;
import tech.reliab.course.zimskovma.bank.service.impl.BankServiceImpl;
import tech.reliab.course.zimskovma.bank.service.impl.CreditAccountServiceImpl;
import tech.reliab.course.zimskovma.bank.service.impl.EmployeeServiceImpl;
import tech.reliab.course.zimskovma.bank.service.impl.PaymentAccountServiceImpl;
import tech.reliab.course.zimskovma.bank.service.impl.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        BankService bankService = new BankServiceImpl();
        Bank bank = bankService.create(new Bank("СБЕР"));
        System.out.println(bank);

        BankOfficeService bankOfficeService = new BankOfficeServiceImpl();
        BankOffice bankOffice = bankOfficeService.create(new BankOffice(
                "Центральный офис",
                "Центр",
                bank
                ));
        System.out.println(bankOffice);

        EmployeeService employeeService = new EmployeeServiceImpl();
        Employee employee = employeeService
                .create(new Employee("Князь Михайлов",
                        bank,
                        bankOffice
                ));
        System.out.println(employee);

        AtmService atmService = new AtmServiceImpl();
        BankAtm bankAtm = atmService.create(new BankAtm("Банкомат центрального офиса",
                bank,
                bankOffice,
                employee
        ));
        System.out.println(bankAtm);

        UserService userService = new UserServiceImpl();
        User user = userService
                .create(new User("Генадий Весёлый",
                        bank
                ));
        System.out.println(user);

        PaymentAccountService paymentAccountService = new PaymentAccountServiceImpl();
        PaymentAccount paymentAccount = paymentAccountService
                .create(new PaymentAccount(user, bank, 1000000
                ));
        System.out.println(paymentAccount);

        CreditAccountService creditAccountService = new CreditAccountServiceImpl();
        CreditAccount creditAccount = creditAccountService.create(new CreditAccount(user, bank, employee, paymentAccount));
        System.out.println(creditAccount);
    }
}