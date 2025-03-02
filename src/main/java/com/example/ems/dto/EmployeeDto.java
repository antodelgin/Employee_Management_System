package com.example.ems.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
/**
 * DTO representing Employee data.
 * Used for data transfer between Controller and Service layers.
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class EmployeeDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(
            description = "Employee ID"
    )
    private Long id;

    @Schema(
            description = "Employee First Name"
    )
    @NotBlank(message = "FirstName is required")
    @Size(min = 2, max = 20, message = "Name must be between 2 and 20 characters")
    private String firstName;

    @Schema(
            description = "Employee Last Name"
    )
    @NotBlank(message = "LastName is required")
    @Size(min = 2, max = 20, message = "Name must be between 2 and 20 characters")
    private String lastName;

    @Schema(
            description = "Employee Email"
    )
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid Email Format")
    private String email;

    @Schema(
            description = "Employee Department"
    )
    @NotBlank(message = "Department is required")
    private String department;

    @Schema(
            description = "Employee Salary"
    )
    @NotNull(message = "Salary is required")
    @Min(value = 1000, message = "Salary must be at least 1000")
    private BigDecimal salary;
}
