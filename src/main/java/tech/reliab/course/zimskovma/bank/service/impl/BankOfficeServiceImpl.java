package tech.reliab.course.zimskovma.bank.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import tech.reliab.course.zimskovma.bank.entity.BankAtm;
import tech.reliab.course.zimskovma.bank.entity.BankOffice;
import tech.reliab.course.zimskovma.bank.entity.Employee;
import tech.reliab.course.zimskovma.bank.service.AtmService;
import tech.reliab.course.zimskovma.bank.service.BankOfficeService;
import tech.reliab.course.zimskovma.bank.service.BankService;
import tech.reliab.course.zimskovma.bank.service.EmployeeService;

public class BankOfficeServiceImpl implements BankOfficeService {

    Map<Integer, BankOffice> bankOfficesTable = new HashMap<Integer, BankOffice>();
    Map<Integer, List<Employee>> employeeByOfficeIdTable = new HashMap<Integer, List<Employee>>();
    Map<Integer, List<BankAtm>> atmByOfficeIdTable = new HashMap<Integer, List<BankAtm>>();

    private final BankService bankService;
    private EmployeeService employeeService;
    private AtmService atmService;

    public BankOfficeServiceImpl(BankService bankService) {
        this.bankService = bankService;
    }

    @Override
    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public void setAtmService(AtmService atmService) {
        this.atmService = atmService;
    }

    @Override
    public List<Employee> getAllEmployeesByOfficeId(int id) {
        return employeeByOfficeIdTable.get(id);
    }

    @Override
    public List<BankOffice> getAllOffices() {
        return new ArrayList<BankOffice>(bankOfficesTable.values());
    }

    @Override
    public BankOffice getBankOfficeById(int id) {
        BankOffice office = bankOfficesTable.get(id);
        if (office == null) {
            System.err.println("Офис не найден");
        }
        return office;
    }

    @Override
    public BankOffice create(BankOffice bankOffice) {
        if (bankOffice == null) {
            return null;
        }

        if (bankOffice.getTotalMoney() < 0) {
            System.err.println("Error: BankOffice - total money must be non-negative");
            return null;
        }

        if (bankOffice.getBank() == null) {
            System.err.println("Error: BankOffice - must have bank");
            return null;
        }

        if (bankOffice.getRentPrice() < 0) {
            System.err.println("Error: BankOffice - rentPrice must be non-negative");
            return null;
        }

        BankOffice newOffice = new BankOffice(bankOffice);
        bankOfficesTable.put(bankOffice.getId(), bankOffice);
        employeeByOfficeIdTable.put(bankOffice.getId(), new ArrayList<>());
        atmByOfficeIdTable.put(bankOffice.getId(), new ArrayList<>());
        bankService.addOffice(bankOffice.getBank().getId(), bankOffice);

        return newOffice;
    }

    @Override
    public void printBankOfficeData(int id) {
        BankOffice bankOffice = getBankOfficeById(id);
        if (bankOffice == null) {
            return;
        }
        System.out.println(bankOffice);
        List<Employee> employees = getAllEmployeesByOfficeId(id);
        if (employees != null) {
            System.out.println("Сотрудники:");
            employees.forEach(System.out::println);
        }
        List<BankAtm> atms = atmByOfficeIdTable.get(id);
        if (atms != null) {
            System.out.println("Банкоматы:");
            atms.forEach(System.out::println);
        }
    }

    @Override
    public List<BankAtm> getSuitableBankAtmInOffice(BankOffice bankOffice, double money) {
        List<BankAtm> bankAtmByOffice = atmByOfficeIdTable.get(bankOffice.getId());
        List<BankAtm> suitableBankAtm = new ArrayList<>();

        for (BankAtm bankAtm : bankAtmByOffice) {
            if (atmService.isAtmSuitable(bankAtm, money)) {
                suitableBankAtm.add(bankAtm);
            }
        }

        return suitableBankAtm;
    }

    @Override
    public boolean addMoney(BankOffice bankOffice, double amount) {
        bankOffice.setTotalMoney(bankOffice.getTotalMoney() + amount);
        bankOffice.getBank().setTotalMoney(bankOffice.getBank().getTotalMoney() + amount);

        return true;
    }

    @Override
    public boolean addAtm(int id, BankAtm bankAtm) {
        BankOffice bankOffice = getBankOfficeById(id);
        if (bankOffice != null && bankAtm != null) {
            if (!bankOffice.getIsAtmPlaceable()) {
                System.err.println("Error: BankOffice - cannot install atm");
                return false;
            }

            bankOffice.setAtmCount(bankOffice.getAtmCount() + 1);
            bankOffice.getBank().setAtmCount(bankOffice.getBank().getAtmCount() + 1);
            bankAtm.setBankOffice(bankOffice);
            bankAtm.setAddress(bankOffice.getAddress());
            bankAtm.setBank(bankOffice.getBank());
            List<BankAtm> officeAtms = atmByOfficeIdTable.get(bankOffice.getId());
            officeAtms.add(bankAtm);
            addMoney(bankOffice, bankAtm.getTotalMoney());

            return true;
        }
        return false;
    }

    @Override
    public boolean addEmployee(int id, Employee employee) {
        BankOffice bankOffice = getBankOfficeById(id);
        if (bankOffice != null && employee != null) {
            bankOffice.getBank().setEmployeeCount(bankOffice.getBank().getEmployeeCount() + 1);
            employee.setBankOffice(bankOffice);
            employee.setBank(bankOffice.getBank());
            List<Employee> officeEmployees = employeeByOfficeIdTable.get(bankOffice.getId());
            officeEmployees.add(employee);
            return true;
        }
        return false;
    }

    @Override
    public List<Employee> getSuitableEmployeeInOffice(BankOffice bankOffice) {
        List<Employee> employees = getAllEmployeesByOfficeId(bankOffice.getId());
        List<Employee> suitableEmployee = new ArrayList<>();

        for (Employee employee : employees) {
            if (employeeService.isEmployeeSuitable(employee)) {
                suitableEmployee.add(employee);
            }
        }

        return suitableEmployee;
    }

    @Override
    public boolean isSuitableBankOffice(BankOffice bankOffice, double money) {
        if (bankOffice.getIsWorking() && bankOffice.getIsCashWithdrawalAvailable()
                && bankOffice.getTotalMoney() >= money) {
            List<BankAtm> bankAtmSuitable = getSuitableBankAtmInOffice(bankOffice, money);
            if (bankAtmSuitable.isEmpty()) {
                return false;
            }

            List<Employee> employeesSuitable = getSuitableEmployeeInOffice(bankOffice);
            if (employeesSuitable.isEmpty()) {
                return false;
            }
            return true;
        }

        return false;
    }
}


