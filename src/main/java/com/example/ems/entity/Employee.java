package com.example.ems.entity;//package com.example.ems.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * Entity representing an Employee in the Employee Management System.
 */
@Entity
@Table(name = "employee_data")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20) // Ensures column is not NULL in DB
    private String firstName;

    @Column(nullable = false, length = 20)
    private String lastName;

    @Column(nullable = false, unique = true) // Enforces unique emails in DB
    private String email;

    @Column(nullable = false)
    private String department;

    @Column(nullable = false)
    private BigDecimal salary;
}

