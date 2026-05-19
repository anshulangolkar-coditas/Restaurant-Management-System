package com.example.growtogether.controller;

import com.example.growtogether.response.ApplicationResponse;
import com.example.growtogether.service.BranchService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/branch")
@RequiredArgsConstructor
public class BranchController {

    private final BranchService branchService;

    @PostMapping("/")
    public ResponseEntity<ApplicationResponse<>> addBranch(@Valid @RequestBody )




}
