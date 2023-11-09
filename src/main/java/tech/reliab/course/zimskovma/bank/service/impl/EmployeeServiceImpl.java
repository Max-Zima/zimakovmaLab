package tech.reliab.course.zimskovma.bank.service.impl;

import tech.reliab.course.zimskovma.bank.entity.Employee;
import tech.reliab.course.zimskovma.bank.service.EmployeeService;
import tech.reliab.course.zimskovma.bank.service.BankOfficeService;

import java.util.*;

public class EmployeeServiceImpl implements EmployeeService {

    Map<Integer, Employee> employeeTable  = new HashMap<Integer, Employee>();
    private final BankOfficeService bankOfficeService;

    public EmployeeServiceImpl(BankOfficeService bankOfficeService) {
        this.bankOfficeService = bankOfficeService;
    }

    @Override
    public Employee create(Employee employee) {
        if (employee == null) {
            return null;
        }

        if (employee.getSalary() < 0) {
            System.err.println("Error: Employee - salary doesn't be negative");
            return null;
        }

        Employee newEmployee = new Employee(employee);
        employeeTable.put(newEmployee.getId(), newEmployee);
        bankOfficeService.addEmployee(newEmployee.getBankOffice().getId(), newEmployee);

        return newEmployee;
    }

    @Override
    public List<Employee> getAllEmployees() {
        return new ArrayList<Employee>(employeeTable.values());
    }

    @Override
    public Employee getEmployeeById(int id) {
        Employee employee = employeeTable.get(id);
        if (employee == null) {
            System.err.println("Employee with id " + id + " is not found");
        }
        return employee;
    }

}
