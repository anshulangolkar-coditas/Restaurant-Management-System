package com.example.growtogether.service;

import com.example.growtogether.dto.branch.request.AddNewBranchRequestDto;
import com.example.growtogether.dto.branch.request.UpdateManagerSalaryRequestDto;
import com.example.growtogether.dto.branch.request.UpdateStaffSalaryRequestDto;
import com.example.growtogether.dto.branch.response.AddNewBranchResponseDto;
import com.example.growtogether.dto.branch.response.UpdateManagerSalaryResponseDto;
import com.example.growtogether.dto.branch.response.UpdateStaffSalaryResponseDto;
import com.example.growtogether.entity.Users;
import jakarta.validation.Valid;

import java.util.List;

public interface BranchService {
    AddNewBranchResponseDto addBranch(@Valid AddNewBranchRequestDto request, Users user);

    List<AddNewBranchResponseDto> getAllBranches(Long restaurantId, int page, Users user);

    UpdateStaffSalaryResponseDto updateStaffSalary(@Valid UpdateStaffSalaryRequestDto request, Users user);

    UpdateManagerSalaryResponseDto updateManagerSalary(@Valid UpdateManagerSalaryRequestDto request, Users user);

    String deleteBranch(Long branchId, Users user);
}
