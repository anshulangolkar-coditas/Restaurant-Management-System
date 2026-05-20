package com.example.growtogether.service;

import com.example.growtogether.dto.staff.response.GetAllStaffResponseDto;
import com.example.growtogether.entity.Users;

import java.util.List;

public interface StaffService {
    List<GetAllStaffResponseDto> getAllStaff(Long branchId, int page, Users user);
}
