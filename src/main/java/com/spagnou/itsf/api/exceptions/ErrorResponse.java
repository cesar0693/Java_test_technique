package com.spagnou.itsf.api.exceptions;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Standard error response structure for API exceptions.
 */
@Data
@NoArgsConstructor
public class ErrorResponse {
    
    private LocalDateTime timestamp;
    private int status;
    private String error;
    private String message;
    private String path;
    
    /**
     * new ErrorResponse.
     *
     * @param status HTTP status code
     * @param error error type
     * @param message detailed error message
     * @param path request path that caused the error
     */
    public ErrorResponse(int status, String error, String message, String path) {
        this.timestamp = LocalDateTime.now();
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
    }
}