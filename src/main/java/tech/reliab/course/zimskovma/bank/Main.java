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
import tech.reliab.course.zimskovma.bank.exception.CreditException;

import tech.reliab.course.zimskovma.bank.exception.IncomException;
import tech.reliab.course.zimskovma.bank.exception.PaymenAccountException;
import tech.reliab.course.zimskovma.bank.exception.RatingException;
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
    public static void main(String[] args) throws CreditException, RatingException, IncomException {
        Random random = new Random();
        BankService bankService = new BankServiceImpl();
        BankOfficeService bankOfficeService = new BankOfficeServiceImpl(bankService);
        bankService.setBankOfficeService(bankOfficeService);

        EmployeeService employeeService = new EmployeeServiceImpl(bankOfficeService);
        bankOfficeService.setEmployeeService(employeeService);

        AtmService atmService = new AtmServiceImpl(bankOfficeService);
        bankOfficeService.setAtmService(atmService);

        bankService.setBankOfficeService(bankOfficeService);

        UserService userService = new UserServiceImpl(bankService);
        bankService.setClientService(userService);
        PaymentAccountService paymentAccountService = new PaymentAccountServiceImpl(userService);
        CreditAccountService creditAccountService = new CreditAccountServiceImpl(userService, bankService);


        for (int i = 0; i < 5; i++) {
            bankService.create(new Bank(Consts.BankNames.get(i)));
        }

        List<Bank> banks = bankService.getAllBanks();
        for (Bank bank : banks) {
            for (int i = 1; i <= 3; i++) {
                bankOfficeService.create(new BankOffice("Офис №" + i + " банка: " + bank.getName(), "г.Белгород, Гражданский проспект, " + String.valueOf(i + random.nextInt(0, 50)), bank, true, true, 0, true, true, true, 0, 100 * i));
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
                atmService.create(new BankAtm("Банкомат № " + i, office.getAddress(), AtmStatus.WORK, office.getBank(), office, bankOfficeService.getAllEmployeesByOfficeId(office.getId()).get(random.nextInt(bankOfficeService.getAllEmployeesByOfficeId(office.getId()).size())), true, true, 10000, random.nextDouble() * 25));
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
            System.out.print("Для того, чтобы получить кредит введите: Кредит \n");
            System.out.println("Для того, чтобы получить файл со счетами клента из системы введите: Вывод");
            System.out.println("Для того, внести информацию о счетах из файла в систему  введите: Ввод");
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
                    int bankId2 = in.nextInt();
                    bankService.printBankData(bankId2);
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
                case ("Кредит"):
                    System.out.println("Список пользователя:");
                    for (User user : userService.getAllUsers()) {
                        System.out.println(user.getId() + ". " + user.getName());
                    }
                    System.out.println("Выбери заёмщика, введите его номер:");
                    int clientId = in.nextInt();
                    in.nextLine();
                    System.out.println("Введите сумму кредита:");
                    double amount = Double.parseDouble(in.nextLine());
                    System.out.println("Введите срок кредита(месяцев)");
                    int months = in.nextInt();
                    in.nextLine();

                    List<Bank> suitableBanks = bankService.getBanksSuitable(amount, months);
                    System.out.println("Список подходящих банков:");
                    for (Bank bank : suitableBanks) {
                        System.out.println(+bank.getId() + ". " + bank.getName());
                    }
                    int bankId = in.nextInt();
                    in.nextLine();
                    Bank bank = bankService.getBankById(bankId);
                    BankOffice bankOffice = bankService.getBankOfficeSuitableInBank(bank, amount)
                            .get(0);
                    Employee employee = bankOfficeService.getSuitableEmployeeInOffice(bankOffice)
                            .get(0);
                    PaymentAccount paymentAccount;
                    paymentAccount = userService.getBestPaymentAccount(clientId);
                    CreditAccount creditAccount = creditAccountService.create(new CreditAccount(
                            userService.getUserById(clientId), bank, LocalDate.now(), months, amount, 0, 0, employee, paymentAccount));
                    if (bankService.approveCredit(bank, creditAccount, employee)) {
                        System.out.println("Кредит одобрен " + creditAccount.getId());
                    } else {
                        System.out.println("Кредит не одобрен");
                    }
                    break;
                case ("Вывод"):
                    System.out.println("Список пользователя:");
                    for (User user : userService.getAllUsers()) {
                        System.out.println(user.getId() + ". " + user.getName());
                    }
                    System.out.println("Выберите пользователя:");
                    int userId2 = in.nextInt();
                    in.nextLine();

                    System.out.println("Список банков:");
                    for (Bank bank2 : bankService.getAllBanks()) {
                        System.out.println(bank2.getId() + ". " + bank2.getName());
                    }
                    System.out.println("Выберите банк:");
                    int bankId3 = in.nextInt();
                    in.nextLine();

                    boolean isOk = creditAccountService.exportClientAccountsToTxt(userId2, bankId3);

                    if (isOk) {
                        System.out.println(
                                "Успешно!");
                    } else {
                        System.out.println("Ошибка. Попробуйте позже.");
                    }
                    break;
                case ("Ввод"):
                    System.out.println("Введите имя файла:");
                    String fileName = in.nextLine();

                    System.out.println("Список банков:");
                    for (Bank bank2 : bankService.getAllBanks()) {
                        System.out.println(bank2.getId() + ". " + bank2.getName());
                    }
                    System.out.println("Выберите банк:");
                    int newBankId = in.nextInt();
                    in.nextLine();

                    boolean isOk2 = creditAccountService.importAccountsTxtAndTransferToBank(fileName, newBankId);
                    if (isOk2) {
                        System.out.println("Успешно!");
                    } else {
                        System.out.println("Ошибка. Попробуйте позже.");
                    }
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