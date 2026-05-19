package com.example.growtogether.service;

import com.example.growtogether.dto.branch.request.AddNewBranchRequestDto;
import com.example.growtogether.dto.branch.response.AddNewBranchResponseDto;
import com.example.growtogether.entity.Users;
import jakarta.validation.Valid;

public interface BranchService {
    AddNewBranchResponseDto addBranch(@Valid AddNewBranchRequestDto request, Users user);
}
