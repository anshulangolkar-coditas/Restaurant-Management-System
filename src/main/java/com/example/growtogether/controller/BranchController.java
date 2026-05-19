package com.example.growtogether.controller;

import com.example.growtogether.dto.branch.request.AddNewBranchRequestDto;
import com.example.growtogether.dto.branch.response.AddNewBranchResponseDto;
import com.example.growtogether.entity.Users;
import com.example.growtogether.response.ApplicationResponse;
import com.example.growtogether.service.BranchService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    public ResponseEntity<ApplicationResponse<AddNewBranchResponseDto>> addBranch(@Valid @RequestBody AddNewBranchRequestDto request, @AuthenticationPrincipal Users user){

        AddNewBranchResponseDto branchDetails =  branchService.addBranch(request, user);

        ApplicationResponse<AddNewBranchResponseDto> response = new ApplicationResponse<>(
                HttpStatus.CREATED.value(),
                "Branch Added Successfully",
                branchDetails
        );
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


}
