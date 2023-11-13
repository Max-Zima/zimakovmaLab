package tech.reliab.course.zimskovma.bank;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

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
import tech.reliab.course.zimskovma.bank.utils.Consts;
import tech.reliab.course.zimskovma.bank.utils.AtmStatus;
import tech.reliab.course.zimskovma.bank.utils.Job;

public class Main {
    public static void main(String[] args) {
        Random random = new Random();
        BankService bankService = new BankServiceImpl();
        BankOfficeService bankOfficeService = new BankOfficeServiceImpl(bankService);
        bankService.setBankOfficeService(bankOfficeService);
        EmployeeService employeeService = new EmployeeServiceImpl(bankOfficeService);
        AtmService atmService = new AtmServiceImpl(bankOfficeService);
        UserService userService = new UserServiceImpl(bankService);
        bankService.setClientService(userService);
        PaymentAccountService paymentAccountService = new PaymentAccountServiceImpl(userService);
        CreditAccountService creditAccountService = new CreditAccountServiceImpl(userService);


        for (int i = 0; i < 5; i++) {
            bankService.create(new Bank(Consts.BankNames.get(i)));
        }

        List<Bank> banks = bankService.getAllBanks();
        for (Bank bank : banks) {
            for (int i = 1; i <= 3; i++) {
                bankOfficeService.create(new BankOffice("Офис №" + i + " банка: " + bank.getName(), "г.Белгород, Гражданский проспект, " + String.valueOf(i + random.nextInt(0, 50)), bank, true, true, 0, true, true, true, 20000, 100 * i));
            }
        }

        List<BankOffice> offices = bankOfficeService.getAllOffices();
        for (BankOffice office : offices) {
            for (int i = 1; i <= 5; i++) {
                employeeService.create(new Employee(Consts.PeopleNames.get(random.nextInt(Consts.PeopleNames.size())), LocalDate.of(random.nextInt(1980, 2000), random.nextInt(1, 13), random.nextInt(1, 29)), Job.Manager, office.getBank(), true, office, true, 10000 * random.nextDouble()));
            }
        }

        for (BankOffice office : offices) {
            for (int i = 1; i <= 3; i++) {
                atmService.create(new BankAtm("Банкомат № " + i, office.getAddress(), AtmStatus.WORK, office.getBank(), office, bankOfficeService.getAllEmployeesByOfficeId(office.getId()).get(random.nextInt(bankOfficeService.getAllEmployeesByOfficeId(office.getId()).size())), true, true, 0, random.nextDouble() * 25));
            }
        }

        for (Bank bank : banks) {
            for (int i = 1; i <= 5; i++) {
                userService.create(new User(Consts.PeopleNames.get(random.nextInt(Consts.PeopleNames.size())), LocalDate.of(random.nextInt(1970, 2003), random.nextInt(1, 13), random.nextInt(1, 29)), Consts.CompanyNames.get(random.nextInt(Consts.CompanyNames.size())), random.nextDouble() * 10000, bank, random.nextInt(100000)));
            }
        }

        List<User> users = userService.getAllUsers();
        for (User user : users) {
            for (int i = 1; i <= 3; i++) {
                paymentAccountService.create(new PaymentAccount(user, user.getBank(), random.nextDouble() * 10000));
            }
        }

        for (User user : users) {
            for (int i = 1; i <= 2; i++) {
                List<BankOffice> bankOffices = bankService.getAllOfficesByBankId(user.getBank().getId());
                BankOffice randomOffice = bankOffices.get(random.nextInt(bankOffices.size()));
                List<Employee> officeEmployees = bankOfficeService.getAllEmployeesByOfficeId(randomOffice.getId());
                Employee randomEmployee = officeEmployees.get(random.nextInt(officeEmployees.size()));
                creditAccountService.create(new CreditAccount(user, user.getBank(), LocalDate.of(random.nextInt(2023, 2027), random.nextInt(1, 13), random.nextInt(1, 29)), LocalDate.of(2028, 12, 13), 60, random.nextInt(10000, 1000000), random.nextInt(10000, 1000000), random.nextInt(100, 100000), user.getBank().getInterestRate(), randomEmployee, userService.getAllPaymentAccountsByClientId(user.getId()).get(random.nextInt(userService.getAllPaymentAccountsByClientId(user.getId()).size()))));
            }
        }

        while (true) {
            System.out.print("Здравствуйте, я банковский помошник, что вы хотите сделать:\n");
            System.out.print("Для того, чтобы получить информацию по банку введите: Банки \n");
            System.out.print("Для того, чтобы получить всю информацию о клиенте введите: Клиенты \n");
            System.out.print("Для того, чтобы выйти введите Q\n");

            Scanner in = new Scanner(System.in);
            String command = in.next();
            switch (command) {
                case ("Банки"):
                    System.out.println("Всего банков = " + bankService.getAllBanks().size());
                    for (Bank bank : bankService.getAllBanks()) {
                        System.out.println(bank.getId() + " - " + bank.getName());
                    }
                    System.out.println("Введите номер, интересующего банка:");
                    int bankId = in.nextInt();
                    bankService.printBankData(bankId);
                    break;
                case ("Клиенты"):
                    System.out.println("Всего клиентов = " + userService.getAllUsers().size());
                    for (User user : userService.getAllUsers()) {
                        System.out.println(user.getId() + " - " + user.getName());
                    }
                    System.out.println("Введите номер, интересующего клиента:");
                    int userId = in.nextInt();
                    userService.printUserData(userId, true);
                    break;
                case ("Выход"):
                    break;
                default:
                    System.out.print("Не корректный ввод\n");
                    break;
            }

        }
    }
}