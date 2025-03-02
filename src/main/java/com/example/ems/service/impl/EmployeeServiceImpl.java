package com.example.ems.service.impl;

import com.example.ems.dto.EmployeeDto;
import com.example.ems.entity.Employee;
import com.example.ems.exception.EmployeeNotFoundException;
import com.example.ems.mapper.EmployeeMapper;
import com.example.ems.repository.EmployeeRepository;
import com.example.ems.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Implementation of EmployeeService.
 * Handles CRUD operations for Employee entities.
 */
@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;
    private EmployeeMapper employeeMapper;

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        Employee employee = employeeMapper.mapToEmployee(employeeDto);
        Employee savedEmployee = employeeRepository.save(employee);
        return employeeMapper.mapToEmployeeDto(savedEmployee);
    }

    @Override
    public EmployeeDto getEmployeeById(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(()->new EmployeeNotFoundException("Employee not found with id: " + id));
        return employeeMapper.mapToEmployeeDto(employee);
    }

    @Override
    public Page<EmployeeDto> getPaginatedEmployees(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Employee> employeePage = employeeRepository.findAll(pageable);
        return employeePage
                .map(employee -> employeeMapper.mapToEmployeeDto(employee));
    }
    @Override
    public EmployeeDto updateEmployee(Long id, EmployeeDto employeeDto) {
        Employee existingEmployee = employeeRepository.findById(id).orElseThrow(()->new EmployeeNotFoundException("Employee not found with id: " + id));
        existingEmployee.setId(employeeDto.getId());
        existingEmployee.setFirstName(employeeDto.getFirstName());
        existingEmployee.setEmail(employeeDto.getEmail());
        existingEmployee.setDepartment(employeeDto.getDepartment());
        existingEmployee.setSalary(employeeDto.getSalary());
        Employee updatedEmployee = employeeRepository.save(existingEmployee);
        return employeeMapper.mapToEmployeeDto(updatedEmployee);
    }

    @Override
    public void deleteEmployee(Long id) {
        employeeRepository.findById(id)
                .orElseThrow(()->new EmployeeNotFoundException("Employee not found with id: " + id));
        employeeRepository.deleteById(id);
    }

}

