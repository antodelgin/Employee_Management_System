package com.example.ems.service;

import com.example.ems.dto.EmployeeDto;
import org.springframework.data.domain.Page;


/**
 * Service interface for Employee Management.
 * Defines CRUD operations for managing employees.
 */
public interface EmployeeService {

    EmployeeDto createEmployee(EmployeeDto employeeDto);

    EmployeeDto getEmployeeById(Long id);

    Page<EmployeeDto> getPaginatedEmployees(int page, int size);

    EmployeeDto updateEmployee(Long id, EmployeeDto employeeDto);

    void deleteEmployee(Long id);
}

