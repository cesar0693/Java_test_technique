package com.spagnou.itsf.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spagnou.itsf.api.services.ChaussetteService;

/**
 * REST controller that exposes Chaussette exercise endpoint.
 * 
 * This controller provides a REST API to interact with the Chausette service.
 * 
 */
@RestController
@RequestMapping("/api/exercices")
public class ChaussetteController {
	@Autowired
    private ChaussetteService chaussetteService;

	/**
     * Gets a list of Chaussette based on the service algorithm.
     * 
     * Endpoint: GET /api/exercices/chaussettes
     *
     * @param start the starting index (default: 0)
     * @param end the ending index(default: 100)
     * @return a list of strings representing the algorithm results
     */
    @GetMapping("/chaussettes")
    public List<String> getChaussettes(
            @RequestParam(defaultValue = "0") int start,
            @RequestParam(defaultValue = "100") int end) {
        
        return chaussetteService.getListChaussettes(start, end);
    }
}
