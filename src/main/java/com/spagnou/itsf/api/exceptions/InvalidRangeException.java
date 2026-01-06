package com.spagnou.itsf.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception thrown when invalid range parameters are provided.
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidRangeException extends RuntimeException {
    
    private static final long serialVersionUID = 1L;

	/**
     * Constructs a new InvalidRangeException with the specified detail message.
     *
     * @param message the detail message
     */
    public InvalidRangeException(String message) {
        super(message);
    }
}