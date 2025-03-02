package com.example.ems.exception;


/**
 * Custom exception thrown when an employee is not found.
 */
public class EmployeeNotFoundException extends RuntimeException{
    private String message;

    public EmployeeNotFoundException(String message) {
        super(message);
    }
}
