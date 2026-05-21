package com.example.growtogether.controller;

import com.example.growtogether.dto.staff.request.DeleteStaffRequestDto;
import com.example.growtogether.dto.staff.response.GetAllGenericResponse;
import com.example.growtogether.dto.staff.response.GetAllStaffResponseDto;
import com.example.growtogether.entity.Users;
import com.example.growtogether.response.ApplicationResponse;
import com.example.growtogether.service.StaffService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/restaurant/branch/staff")
public class StaffController {

    private final StaffService staffService;

    @PreAuthorize("hasAnyRole('ADMIN','OWNER','MANAGER')")
    @GetMapping("/")
    public ResponseEntity<ApplicationResponse<List<? extends GetAllGenericResponse>>> getAllStaff(@RequestParam(required = false) Long branchId, @RequestParam int page, @AuthenticationPrincipal Users user){

        List<? extends GetAllGenericResponse> staffList = staffService.getAllStaff(branchId, page, user);

        ApplicationResponse<List<? extends GetAllGenericResponse>> response = new ApplicationResponse<>(
                HttpStatus.OK.value(),
                "Retrieved All Staff Successfully",
                staffList
        );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ADMIN','OWNER','MANAGER')")
    @DeleteMapping("/")
    public ResponseEntity<ApplicationResponse<String>> deleteStaff(@Valid @RequestBody DeleteStaffRequestDto request, @AuthenticationPrincipal Users user){

        String message = staffService.deleteStaff(request, user);

        ApplicationResponse<String> response = new ApplicationResponse<>(
                HttpStatus.OK.value(),
                "SUCCESS",
                message
        );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }




}
