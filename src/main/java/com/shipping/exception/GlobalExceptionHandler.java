package com.shipping.exception;

import com.shipping.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleOrderNotFound(OrderNotFoundException ex) {
        ErrorResponse error = ErrorResponse.of(
                HttpStatus.NOT_FOUND,
                ex.getMessage(),
                "ORD-404" // Custom error code
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    // Optional: Handle other unexpected exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(Exception ex) {
        ErrorResponse error = ErrorResponse.of(
                HttpStatus.INTERNAL_SERVER_ERROR,
                ex.getMessage(),
                "GEN-500"
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
}
