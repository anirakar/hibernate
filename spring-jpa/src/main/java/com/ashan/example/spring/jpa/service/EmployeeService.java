package com.ashan.example.spring.jpa.service;

import com.ashan.example.spring.jpa.entity.Employee;
import com.ashan.example.spring.jpa.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EmployeeService {

    private EmployeeRepository repository;

    public Employee createEmployee(Employee employee) {
        return repository.insert(employee);
    }

    public List<Employee> getAllEmployee() {
        return repository.findAll();
    }
}
