package tech.reliab.course.zimskovma.bank.service.impl;

import tech.reliab.course.zimskovma.bank.entity.BankOffice;
import tech.reliab.course.zimskovma.bank.entity.Employee;
import tech.reliab.course.zimskovma.bank.service.EmployeeService;

public class EmployeeServiceImpl implements EmployeeService {

    @Override
    public Employee create(Employee employee) {
        if (employee == null) {
            return null;
        }

        if (employee.getSalary().signum() < 0) {
            System.err.println("Error: Employee - salary doesn't be negative");
            return null;
        }

        return new Employee(employee);
    }

}
