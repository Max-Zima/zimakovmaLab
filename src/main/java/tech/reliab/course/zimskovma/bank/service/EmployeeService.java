package tech.reliab.course.zimskovma.bank.service;

import tech.reliab.course.zimskovma.bank.entity.Employee;

import java.util.List;

public interface EmployeeService {
    boolean isEmployeeSuitable(Employee employee);

    Employee create(Employee employee);

    public Employee getEmployeeById(int id);

    public List<Employee> getAllEmployees();
}
