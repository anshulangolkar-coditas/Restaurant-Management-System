package com.example.growtogether.service;

import com.example.growtogether.dto.staff.request.DeleteStaffRequestDto;
import com.example.growtogether.dto.staff.response.GetAllGenericResponse;
import com.example.growtogether.dto.staff.response.GetAllStaffResponseDto;
import com.example.growtogether.entity.Users;
import jakarta.validation.Valid;

import java.util.List;

public interface StaffService {
    List<? extends GetAllGenericResponse> getAllStaff(Long branchId, int page, Users user);

    String deleteStaff(@Valid DeleteStaffRequestDto request, Users user);
}
