package com.spagnou.itsf.api.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.spagnou.itsf.api.exceptions.InvalidRangeException;

/**
 * Service that handles ChaussetteSales.
 *
 * This service implements an algorithm that generates
 * a list of strings based on some rules.s
 *
 */
@Service
public class ChaussetteService {
	
    private static final int MAX_RANGE = 10000; // Random max
    
	 /**
     * Generates a list of strings based on the following rules:
     * 
     *   If the number is divisible by 3: adds "Chaussettes"
     *   If the number is divisible by 5: adds "Sales"
     *   If the number is divisible by both 3 and 5: returns "ChaussettesSales"
     *   Otherwise: returns the number as a string
     *
     * @param start the starting index
     * @param end the ending index
     * @return a list of strings representing the algorithm results
     */
	public List<String> getListChaussettes(int start, int end) {
		validateRange(start, end); // Validation
		  
        List<String> results = new ArrayList<>();

        for (int i = start; i <= end; i++) {
            String result = "";
            if (i % 3 == 0) {
                result += "Chaussettes";
            }
            if (i % 5 == 0) {
                result += "Sales";
            }
            results.add("".equals(result) ? String.valueOf(i) : result);
        }   
        return results;
    }
	
	/**
     * Validates the range parameters.
     *
     * @param start the starting index
     * @param end the ending index
     * @throws InvalidRangeException if validation fails
     */
    private void validateRange(int start, int end) {
        if (start > end) {
            throw new InvalidRangeException(
                "Start value (" + start + ") must be less than or equal to end value (" + end + ")"
            );
        }
        
        if (end - start > MAX_RANGE) {
            throw new InvalidRangeException(
                "Range is too large. The maximum allowed range is " + MAX_RANGE
            );
        }
    }
}
