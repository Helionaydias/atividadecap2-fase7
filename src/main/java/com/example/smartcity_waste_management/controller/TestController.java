package com.example.smartcity_waste_management.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.smartcity_waste_management.exception.ResourceNotFoundException;

@RestController
public class TestController {

    @GetMapping("/test/not-found")
    public ResponseEntity<String> testNotFound(@RequestParam String id) {
        throw new ResourceNotFoundException("Resource with ID " + id + " not found.");
    }

    @GetMapping("/test/validation")
    public ResponseEntity<String> testValidation(@RequestParam String param) {
        if (param.length() < 5) {
            throw new IllegalArgumentException("Param must have at least 5 characters.");
        }
        return ResponseEntity.ok("Valid param");
    }
}
