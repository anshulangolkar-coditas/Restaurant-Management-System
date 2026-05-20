package com.example.growtogether.dtomapping.branch;

import com.example.growtogether.dto.branch.request.AddNewBranchRequestDto;
import com.example.growtogether.dto.branch.response.AddNewBranchResponseDto;
import com.example.growtogether.dto.branch.response.UpdateManagerSalaryResponseDto;
import com.example.growtogether.dto.branch.response.UpdateStaffSalaryResponseDto;
import com.example.growtogether.dto.restaurant.response.AddRestaurantResponseDto;
import com.example.growtogether.entity.*;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BranchMapping {

    public RestaurantBranch dtoToEntity(AddNewBranchRequestDto request, Restaurant restaurant){

        return RestaurantBranch.builder()
                .branchName(request.getBranchName())
                .branchAddress(request.getBranchAddress())
                .contactNumber(request.getNumber())
                .numberOfTables(request.getNumberOfTables())
                .restaurant(restaurant)
                .build();
    }

    public AddNewBranchResponseDto entityToDto(RestaurantBranch branch, AddRestaurantResponseDto restaurant){

        return AddNewBranchResponseDto.builder()
                .branchId(branch.getBranchId())
                .branchName(branch.getBranchName())
                .address(branch.getBranchAddress())
                .number(branch.getContactNumber())
                .numberOfTables(branch.getNumberOfTables())
                .restaurant(restaurant)
                .build();
    }

    public List<AddNewBranchResponseDto> listEntityToDto(Page<RestaurantBranch> branch, AddRestaurantResponseDto restaurant){

        return branch.stream()
                .map(b -> entityToDto(b,restaurant)).toList();
    }

    public UpdateStaffSalaryResponseDto updateStaffSalaryToDto(Staff staff, Users staffUser){

        return UpdateStaffSalaryResponseDto.builder()
                .staffId(staff.getStaffId())
                .fistName(staffUser.getFistName())
                .lastName(staffUser.getLastName())
                .role(staffUser.getRole())
                .salary(staff.getSalary())
                .build();

    }

    public UpdateManagerSalaryResponseDto updateManagerSalaryToDto(Manager manager, Users staffUser){

        return UpdateManagerSalaryResponseDto.builder()
                .staffId(manager.getManagerId())
                .fistName(staffUser.getFistName())
                .lastName(staffUser.getLastName())
                .role(staffUser.getRole())
                .salary(manager.getSalary())
                .build();

    }


}
