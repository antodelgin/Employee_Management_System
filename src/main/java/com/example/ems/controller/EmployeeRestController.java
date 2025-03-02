package com.example.ems.controller;

import com.example.ems.dto.EmployeeDto;
import com.example.ems.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Employee Management API", description = "REST API for employee CRUD operations")
@RestController
@RequestMapping("/api/employees")
@AllArgsConstructor
public class EmployeeRestController {

    private final EmployeeService employeeService;

    /**
     * Create a new employee.
     */
    @Operation(summary = "Add new employee", description = "Creates a new employee")
    @ApiResponse(responseCode = "201", description = "Employee created successfully")
    @PostMapping
    public ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeDto employeeDto) {
        EmployeeDto savedEmployee = employeeService.createEmployee(employeeDto);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }


    /**
     * Get a single employee by ID.
     */
    @Operation(summary = "Get employee by ID", description = "Retrieve a single employee by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Employee found"),
            @ApiResponse(responseCode = "404", description = "Employee not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable Long id) {
        EmployeeDto employeeDto =  employeeService.getEmployeeById(id);
        return ResponseEntity.ok(employeeDto);
    }

    /**
     * Get paginated list of employees.
     */
    @Operation(summary = "Get all employees", description = "Returns a paginated list of employees")
    @ApiResponse(responseCode = "200", description = "List retrieved successfully")
    @GetMapping
    public ResponseEntity<Page<EmployeeDto>> getEmployees(@RequestParam(defaultValue = "0") int page,
                                                       @RequestParam(defaultValue = "8") int size) {
        Page<EmployeeDto> employees = employeeService.getPaginatedEmployees(page, size);
        return ResponseEntity.ok(employees);

    }


    /**
     * Update an existing employee.
     */
    @Operation(summary = "Update employee", description = "Updates an existing employee")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Employee updated successfully"),
            @ApiResponse(responseCode = "404", description = "Employee not found")
    })
    @PutMapping("/{id}")
        public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable("id") Long id,@RequestBody EmployeeDto updatedEmployee){
            EmployeeDto employeeDto=employeeService.updateEmployee(id,updatedEmployee);
            return ResponseEntity.ok(employeeDto);
        }

    /**
     * Delete an employee by ID.
     */
    @Operation(summary = "Delete employee", description = "Deletes an employee by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Employee deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Employee not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return new ResponseEntity<>("Employee deleted successfully", HttpStatus.OK);
    }
}
