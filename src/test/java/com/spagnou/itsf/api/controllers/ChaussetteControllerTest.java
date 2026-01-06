package com.spagnou.itsf.api.controllers;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.spagnou.itsf.api.controllers.ChaussetteController;
import com.spagnou.itsf.api.exceptions.InvalidRangeException;
import com.spagnou.itsf.api.services.ChaussetteService;

/**
 * Unit tests for ChaussetteController.
 */
@WebMvcTest(ChaussetteController.class)
class ChaussetteControllerTest {
    
    @Autowired
    private MockMvc mockMvc;
    
    @MockitoBean
    private ChaussetteService chaussetteService;
    
    @Test
    void testGetChaussettes_WithDefaultParameters() throws Exception {
        // Given
        List<String> expectedResult = Arrays.asList("1", "2", "Chaussettes");
        when(chaussetteService.getListChaussettes(0, 100))
            .thenReturn(expectedResult);
        
        // When & Then
        mockMvc.perform(get("/api/exercices/chaussettes"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$.length()").value(3))
            .andExpect(jsonPath("$[0]").value("1"))
            .andExpect(jsonPath("$[1]").value("2"))
            .andExpect(jsonPath("$[2]").value("Chaussettes"));
        
        verify(chaussetteService, times(1)).getListChaussettes(0, 100);
    }
    
    @Test
    void testGetChaussettes_WithCustomParameters() throws Exception {
        // Given
        List<String> expectedResult = Arrays.asList("1", "2", "Chaussettes", "4", "Sales");
        when(chaussetteService.getListChaussettes(1, 5))
            .thenReturn(expectedResult);
        
        // When & Then
        mockMvc.perform(get("/api/exercices/chaussettes")
                .param("start", "1")
                .param("end", "5"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$.length()").value(5));
        
        verify(chaussetteService, times(1)).getListChaussettes(1, 5);
    }
    
    @Test
    void testGetChaussettes_WithInvalidRange_ReturnsBadRequest() throws Exception {
        // Given
        when(chaussetteService.getListChaussettes(10, 5))
            .thenThrow(new InvalidRangeException("Start value must be less than or equal to end value"));
        
        // When & Then
        mockMvc.perform(get("/api/exercices/chaussettes")
                .param("start", "10")
                .param("end", "5"))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.status").value(400))
            .andExpect(jsonPath("$.error").value("Bad Request"))
            .andExpect(jsonPath("$.message").value("Start value must be less than or equal to end value"))
            .andExpect(jsonPath("$.path").exists())
            .andExpect(jsonPath("$.timestamp").exists());
        
        verify(chaussetteService, times(1)).getListChaussettes(10, 5);
    }
    
    @Test
    void testGetChaussettes_WithTooLargeRange_ReturnsBadRequest() throws Exception {
        // Given
        when(chaussetteService.getListChaussettes(0, 20000))
            .thenThrow(new InvalidRangeException("Range is too large. Maximum allowed range is 10000"));
        
        // When & Then
        mockMvc.perform(get("/api/exercices/chaussettes")
                .param("start", "0")
                .param("end", "20000"))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.message").value("Range is too large. Maximum allowed range is 10000"));
        
        verify(chaussetteService, times(1)).getListChaussettes(0, 20000);
    }
    
    @Test
    void testGetChaussettes_WithNegativeNumbers() throws Exception {
        // Given
        List<String> expectedResult = Arrays.asList("Sales", "-4", "Chaussettes");
        when(chaussetteService.getListChaussettes(-5, -3))
            .thenReturn(expectedResult);
        
        // When & Then
        mockMvc.perform(get("/api/exercices/chaussettes")
                .param("start", "-5")
                .param("end", "-3"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.length()").value(3));
        
        verify(chaussetteService, times(1)).getListChaussettes(-5, -3);
    }
}