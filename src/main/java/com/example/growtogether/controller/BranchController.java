package com.example.growtogether.controller;

import com.example.growtogether.dto.branch.request.AddNewBranchRequestDto;
import com.example.growtogether.dto.branch.request.UpdateManagerSalaryRequestDto;
import com.example.growtogether.dto.branch.request.UpdateStaffSalaryRequestDto;
import com.example.growtogether.dto.branch.response.AddNewBranchResponseDto;
import com.example.growtogether.dto.branch.response.UpdateManagerSalaryResponseDto;
import com.example.growtogether.dto.branch.response.UpdateStaffSalaryResponseDto;
import com.example.growtogether.entity.Users;
import com.example.growtogether.response.ApplicationResponse;
import com.example.growtogether.service.BranchService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/branch")
@RequiredArgsConstructor
public class BranchController {

    private final BranchService branchService;

    @PostMapping("/")
    public ResponseEntity<ApplicationResponse<AddNewBranchResponseDto>> addBranch(@Valid @RequestBody AddNewBranchRequestDto request, @AuthenticationPrincipal Users user) {

        AddNewBranchResponseDto branchDetails = branchService.addBranch(request, user);

        ApplicationResponse<AddNewBranchResponseDto> response = new ApplicationResponse<>(
                HttpStatus.CREATED.value(),
                "Branch Added Successfully",
                branchDetails
        );
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<ApplicationResponse<List<AddNewBranchResponseDto>>> getAllBranches(@RequestParam Long restaurantId, @RequestParam int page, @AuthenticationPrincipal Users user) {

        List<AddNewBranchResponseDto> branchList = branchService.getAllBranches(restaurantId, page, user);

        ApplicationResponse<List<AddNewBranchResponseDto>> response = new ApplicationResponse<>(
                HttpStatus.OK.value(),
                "Fetched All the Branches Successfully",
                branchList
        );

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PatchMapping("/update-staff-salary")
    public ResponseEntity<ApplicationResponse<UpdateStaffSalaryResponseDto>> updateStaffSalary(@Valid @RequestBody UpdateStaffSalaryRequestDto request, @AuthenticationPrincipal Users user) {

        UpdateStaffSalaryResponseDto staffDetails = branchService.updateStaffSalary(request, user);

        ApplicationResponse<UpdateStaffSalaryResponseDto> response = new ApplicationResponse<>(
                HttpStatus.OK.value(),
                "Salary Updated!!!",
                staffDetails
        );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PatchMapping("/update-manager-salary")
    public ResponseEntity<ApplicationResponse<UpdateManagerSalaryResponseDto>> updateManagerSalary(@Valid @RequestBody UpdateManagerSalaryRequestDto request, @AuthenticationPrincipal Users user) {

        UpdateManagerSalaryResponseDto staffDetails = branchService.updateManagerSalary(request, user);

        ApplicationResponse<UpdateManagerSalaryResponseDto> response = new ApplicationResponse<>(
                HttpStatus.OK.value(),
                "Salary Updated!!!",
                staffDetails
        );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{branchId}")
    public ResponseEntity<ApplicationResponse<String>> deleteBranch(@PathVariable Long branchId, @AuthenticationPrincipal Users user) {

        String details = branchService.deleteBranch(branchId, user);

        ApplicationResponse<String> response = new ApplicationResponse<>(
                HttpStatus.OK.value(),
                "Salary Updated!!!",
                details
        );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}
