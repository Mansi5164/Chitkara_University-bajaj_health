package com.mansi.springboot_api.controller;

import com.mansi.springboot_api.dto.ApiResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {

    @Value("${official.email}")
    private String email;

    @GetMapping("/health")
    public ApiResponse health(){
        return new ApiResponse(true, email, null);
    }
}

