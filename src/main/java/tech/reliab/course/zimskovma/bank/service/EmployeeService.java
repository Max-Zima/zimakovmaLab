package tech.reliab.course.zimskovma.bank.service;

import tech.reliab.course.zimskovma.bank.entity.Employee;

import java.util.List;

public interface EmployeeService {
    Employee create(Employee employee);

    public Employee getEmployeeById(int id);

    public List<Employee> getAllEmployees();
}
