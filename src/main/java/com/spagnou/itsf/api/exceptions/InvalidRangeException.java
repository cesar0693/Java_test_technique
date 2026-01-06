package com.spagnou.itsf.api.exceptions;

/**
 * Exception thrown when invalid range parameters are provided.
 */
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