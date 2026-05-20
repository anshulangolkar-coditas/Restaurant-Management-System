package com.example.growtogether.controller;

import com.example.growtogether.dto.staff.response.GetAllStaffResponseDto;
import com.example.growtogether.entity.Users;
import com.example.growtogether.response.ApplicationResponse;
import com.example.growtogether.service.StaffService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/restaurant/branch/staff")
public class StaffController {

    private final StaffService staffService;

    @PreAuthorize("hasAnyRole('ADMIN','OWNER','MANAGER')")
    @GetMapping("/")
    public ResponseEntity<ApplicationResponse<List<GetAllStaffResponseDto>>> getAllStaff(@RequestParam(required = false) Long branchId, @RequestParam int page, @AuthenticationPrincipal Users user){

        List<GetAllStaffResponseDto> staffList = staffService.getAllStaff(branchId, page, user);

        ApplicationResponse<List<GetAllStaffResponseDto>> response = new ApplicationResponse<>(
                HttpStatus.OK.value(),
                "Retrieved All Staff Successfully",
                staffList
        );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }



}
