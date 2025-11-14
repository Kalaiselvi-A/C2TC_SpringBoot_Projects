package com.tnsif.employeeservice.exception;

// Suppresses compiler warnings about the missing serialVersionUID in Serializable classes
@SuppressWarnings("serial")
public class ResourceNotFoundException extends RuntimeException {  // Custom exception extending RuntimeException

    // Constructor that accepts a custom error message
    public ResourceNotFoundException(String message) {
        super(message);  // Pass the message to the parent RuntimeException class
    }
}
