package com.vacation.service.impl;

import com.vacation.entity.Employee;
import com.vacation.exception.ResourceNotFoundException;
import com.vacation.repo.EmployeeRepository;
import com.vacation.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Employee saveEmployee(String name) {
        Employee emp=new Employee();
        emp.setName(name);
        return employeeRepository.save(emp);
    }

   @Override
    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id).orElseThrow(()->
                new ResourceNotFoundException("Employee Not Found Exception"));
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }
}