package com.tnsif.employeeservice.exception;

import org.springframework.http.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.*;

@ControllerAdvice //  Enables global exception handling across all controllers
public class GlobalExceptionHandler {

    // ======================================================================
    // HANDLE RESOURCE NOT FOUND EXCEPTION (Custom Exception)
    // ======================================================================
    @ExceptionHandler(ResourceNotFoundException.class)   // Catch ResourceNotFoundException
    public ResponseEntity<Object> handleResourceNotFound(ResourceNotFoundException ex, WebRequest request) {

        // Prepare error response body
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());          // Error time
        body.put("status", HttpStatus.NOT_FOUND.value());    // 404 status code
        body.put("error", "Resource Not Found");             // Error type
        body.put("message", ex.getMessage());                // Custom exception message
        body.put("path", request.getDescription(false));     // API endpoint path

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND); // Return 404 response
    }

    // ======================================================================
    // HANDLE VALIDATION ERRORS (@Valid)
    // ======================================================================
    @ExceptionHandler(MethodArgumentNotValidException.class)  // Triggered when @Valid fails
    public ResponseEntity<Object> handleValidationErrors(MethodArgumentNotValidException ex) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());            // Error time
        body.put("status", HttpStatus.BAD_REQUEST.value());    // 400 status code
        body.put("error", "Validation Failed");                // Error type

        // Collect all validation field errors
        List<String> errors = new ArrayList<>();
        ex.getBindingResult().getFieldErrors().forEach(err ->
                errors.add(err.getField() + ": " + err.getDefaultMessage()));

        body.put("messages", errors); // Add list of validation messages

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST); // Return 400 response
    }

    // ======================================================================
    // HANDLE ANY OTHER GENERAL EXCEPTION
    // ======================================================================
    @ExceptionHandler(Exception.class)       // Catch all unhandled exceptions
    public ResponseEntity<Object> handleGeneralException(Exception ex, WebRequest request) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());                // Error time
        body.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value()); // 500 status code
        body.put("error", "Internal Server Error");                // Generic error type
        body.put("message", ex.getMessage());                      // Exception message
        body.put("path", request.getDescription(false));           // API path

        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR); // Return 500 response
    }
}
