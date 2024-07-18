package com.vacation.service;

import com.vacation.entity.Employee;

import java.util.List;

public interface EmployeeService {
    Employee saveEmployee(String employeeName);

    Employee getEmployeeById(Long employeeId);

    List<Employee> getAllEmployees();
}
