package com.spagnou.itsf.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

/**
 * Global exception handler for the application.
 * 
 * This class handles all exceptions thrown by controllers and provides
 * custom error responses.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    
    /**
     * Handles InvalidRangeException.
     *
     * @param ex the exception
     * @param request the web request
     * @return error response with HTTP 400 status
     */
    @ExceptionHandler(InvalidRangeException.class)
    public ResponseEntity<ErrorResponse> handleInvalidRangeException(
            InvalidRangeException ex, 
            WebRequest request) {
        
        ErrorResponse errorResponse = new ErrorResponse(
            HttpStatus.BAD_REQUEST.value(),
            HttpStatus.BAD_REQUEST.getReasonPhrase(),
            ex.getMessage(),
            request.getDescription(false).replace("uri=", "")
        );
        
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
    
    /**
     * Handles all other unexpected exceptions.
     *
     * @param ex the exception
     * @param request the web request
     * @return error response with HTTP 500 status
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGlobalException(
            Exception ex, 
            WebRequest request) {
        
        ErrorResponse errorResponse = new ErrorResponse(
            HttpStatus.INTERNAL_SERVER_ERROR.value(),
            HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
            "An unexpected error occurred",
            request.getDescription(false)
        );
        
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}