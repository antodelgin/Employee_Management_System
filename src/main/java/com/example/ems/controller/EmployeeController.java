package com.example.ems.controller;


import com.example.ems.dto.EmployeeDto;
import com.example.ems.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

/**
 * Employee Management Controller.
 * Handles CRUD operations for employees and provides paginated employee list via Thymeleaf views.
 */
@Controller
@AllArgsConstructor
public class EmployeeController {

    private EmployeeService employeeService;

    /**
     * Home page for the Employee Management System.
     */
    @GetMapping
    public String home(){
        return "index";
    }

    /**
     * Displays the Add Employee form.
     */
    @GetMapping("/add-employee")
    public String showAddEmployeeForm(Model model){
        EmployeeDto employeeDto= new EmployeeDto();
        employeeDto.setDepartment("");
        model.addAttribute("employee",employeeDto);
        return "add-employee";
    }

    /**
     * Saves an employee (Create or Update).
     */
    @PostMapping("/save-employee")
    public String saveEmployee(@Valid @ModelAttribute("employee") EmployeeDto employeeDto, BindingResult result){
        if (result.hasErrors()) {
            if (employeeDto.getId() == null) {
                return "add-employee";  // If ID is null, it's a new employee -> return add form
            } else {
                return "update-employee";  // If ID exists, it's an update -> return update form
            }
        }
        employeeService.createEmployee(employeeDto);
        return "redirect:/employees-list";
    }

    /**
     * Fetches an employee by ID.
     */
    @GetMapping("/employee/{id}")
    public String getEmployeeById(@PathVariable Long id, Model model){
       EmployeeDto employeeDto = employeeService.getEmployeeById(id);
        model.addAttribute("employee", employeeDto);
        return "employee-details";
    }

    /**
     * Fetches a paginated list of employees.
     */
    @GetMapping("/employees-list")
    public String getEmployees(@RequestParam(defaultValue = "0") int page,
                                @RequestParam(defaultValue = "8") int size, Model model) {
        Page<EmployeeDto> employeePage = employeeService.getPaginatedEmployees(page, size);

        model.addAttribute("employeePage", employeePage);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", employeePage.getTotalPages());

        return "employees-list"; // Thymeleaf template
    }

    /**
     * Displays the Update Employee form.
     */
    @GetMapping("/update-employee/{id}")
    public String updateEmployee(@PathVariable Long id, Model model){
        EmployeeDto employeeDto = employeeService.getEmployeeById(id);
        model.addAttribute("employee",employeeDto);
        return "/update-employee";

    }


    /**
     * Deletes an employee by ID.
     */
    @GetMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable Long id){
        employeeService.deleteEmployee(id);
        return "redirect:/employees-list";
    }

}
