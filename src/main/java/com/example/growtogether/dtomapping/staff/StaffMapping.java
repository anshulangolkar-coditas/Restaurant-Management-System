package com.example.growtogether.dtomapping.staff;

import com.example.growtogether.dto.staff.response.GetAllRestaurantAndStaffResponseDto;
import com.example.growtogether.dto.staff.response.GetAllStaffResponseDto;
import com.example.growtogether.entity.Restaurant;
import com.example.growtogether.entity.RestaurantBranch;
import com.example.growtogether.entity.Staff;
import com.example.growtogether.entity.Users;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StaffMapping {

    public GetAllStaffResponseDto staffDetailsToDto(Staff staff, Users user, Restaurant restaurant, RestaurantBranch branch){

        return GetAllStaffResponseDto.builder()
                .staffId(staff.getStaffId())
                .fistName(user.getFistName())
                .lastName(user.getLastName())
                .salary(staff.getSalary())
                .restaurantId(restaurant.getRestaurantId())
                .restaurantName(restaurant.getRestaurantName())
                .branchId(branch.getBranchId())
                .branchName(branch.getBranchName())
                .build();
    }

    public List<GetAllStaffResponseDto> listStaffDetailsToDto(Page<Staff> staff/*, List<Users> user*/, Restaurant restaurant, RestaurantBranch branch){

        return staff.stream()
                .map(s -> staffDetailsToDto(s, s.getUser(), restaurant, branch))
                .toList();
    }


    public GetAllRestaurantAndStaffResponseDto getAllStaffAndManagerDetailsDto(Staff staff, Users user, Restaurant restaurant, RestaurantBranch branch){

        return GetAllRestaurantAndStaffResponseDto.builder()
                .staffId(staff.getStaffId())
                .fistName(user.getFistName())
                .lastName(user.getLastName())
                .salary(staff.getSalary())
                .restaurantId(restaurant.getRestaurantId())
                .restaurantName(restaurant.getRestaurantName())
                .branchId(branch.getBranchId())
                .branchName(branch.getBranchName())
                .managerId(staff.getBranch().getManager().getManagerId())
                .managerName(staff.getBranch().getManager().getUser().getFistName()+" "+staff.getBranch().getManager().getUser().getLastName())
                .build();
    }







}
