package com.mansi.springboot_api.controller;

import com.mansi.springboot_api.dto.ApiResponse;
import com.mansi.springboot_api.dto.BfhlRequest;
import com.mansi.springboot_api.service.BfhlService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class BfhlController {

    private final BfhlService service;
    public BfhlController(BfhlService service){
        this.service = service;
    }

    @Value("${official.email}")
    private String email;

    @PostMapping("/bfhl")
    public ResponseEntity<ApiResponse> process(@Valid @RequestBody BfhlRequest request){
        Object result = service.process(request);
        return ResponseEntity.ok(new ApiResponse(true, email, result));
    }


}
