package com.example.ems.repository;

import com.example.ems.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for Employee entity.
 */
public interface EmployeeRepository extends JpaRepository<com.example.ems.entity.Employee, Long> {

}
