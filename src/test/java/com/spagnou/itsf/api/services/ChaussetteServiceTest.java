package com.spagnou.itsf.api.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.spagnou.itsf.api.exceptions.InvalidRangeException;
import com.spagnou.itsf.api.services.ChaussetteService;

/**
 * Unit tests for ChaussetteService.
 */
public class ChaussetteServiceTest {

private ChaussetteService chaussetteService;
    
    @BeforeEach
    void setUp() {
        chaussetteService = new ChaussetteService();
    }
    
    @Test
    void testGetListChaussettes_BasicRange() {
        // Given
        int start = 1;
        int end = 5;
        
        // When
        List<String> result = chaussetteService.getListChaussettes(start, end);
        
        // Then
        assertNotNull(result);
        assertEquals(5, result.size());
        assertEquals("1", result.get(0));
        assertEquals("2", result.get(1));
        assertEquals("Chaussettes", result.get(2));
        assertEquals("4", result.get(3));
        assertEquals("Sales", result.get(4));
    }
    
    @Test
    void testGetListChaussettes_WithMultiples() {
        // Given
        int start = 1;
        int end = 15;
        
        // When
        List<String> result = chaussetteService.getListChaussettes(start, end);
        
        // Then
        assertEquals("Chaussettes", result.get(2));
        assertEquals("Sales", result.get(4));
        assertEquals("Chaussettes", result.get(5));
        assertEquals("Chaussettes", result.get(8));
        assertEquals("Sales", result.get(9));
        assertEquals("Chaussettes", result.get(11));
        assertEquals("ChaussettesSales", result.get(14));
    }
    
    @Test
    void testGetListChaussettes_StartFromZero() {
        // Given
        int start = 0;
        int end = 3;
        
        // When
        List<String> result = chaussetteService.getListChaussettes(start, end);
        
        // Then
        assertEquals(4, result.size());
        assertEquals("ChaussettesSales", result.get(0)); // 0 is divisible by both
        assertEquals("1", result.get(1));
        assertEquals("2", result.get(2));
        assertEquals("Chaussettes", result.get(3));
    }
    
    @Test
    void testGetListChaussettes_SingleValue() {
        // Given
        int start = 15;
        int end = 15;
        
        // When
        List<String> result = chaussetteService.getListChaussettes(start, end);
        
        // Then
        assertEquals(1, result.size());
        assertEquals("ChaussettesSales", result.get(0));
    }
    
    @Test
    void testGetListChaussettes_StartGreaterThanEnd_ThrowsException() {
        // Given
        int start = 10;
        int end = 5;
        
        // When & Then
        InvalidRangeException exception = assertThrows(
            InvalidRangeException.class,
            () -> chaussetteService.getListChaussettes(start, end)
        );
        
        assertTrue(exception.getMessage().contains("must be less than or equal to"));
    }
    
    @Test
    void testGetListChaussettes_RangeTooLarge_ThrowsException() {
        // Given
        int start = 0;
        int end = 10001; // If MAX_RANGE is 10000 (random)
        
        // When & Then
        InvalidRangeException exception = assertThrows(
            InvalidRangeException.class,
            () -> chaussetteService.getListChaussettes(start, end)
        );
        
        assertTrue(exception.getMessage().contains("Range is too large"));
    }
    
    @Test
    void testGetListChaussettes_NegativeNumbers() {
        // Given
        int start = -5;
        int end = 5;
        
        // When
        List<String> result = chaussetteService.getListChaussettes(start, end);
        
        // Then
        assertEquals(11, result.size());
        assertEquals("Sales", result.get(0));
        assertEquals("Chaussettes", result.get(2)); 
        assertEquals("ChaussettesSales", result.get(5)); 
        assertEquals("Chaussettes", result.get(8)); 
        assertEquals("Sales", result.get(10));
    }
}
