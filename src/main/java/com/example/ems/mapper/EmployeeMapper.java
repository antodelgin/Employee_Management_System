package com.example.ems.mapper;

import com.example.ems.dto.EmployeeDto;
import com.example.ems.entity.Employee;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper {

    private final ModelMapper modelMapper;

    public EmployeeMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public EmployeeDto mapToEmployeeDto(Employee employee) {
        return modelMapper.map(employee, EmployeeDto.class);
    }

    public Employee mapToEmployee(EmployeeDto employeeDto) {
        return modelMapper.map(employeeDto, Employee.class);
    }
}

