package com.example.growtogether.dtomapping.branch;

import com.example.growtogether.dto.branch.request.AddNewBranchRequestDto;
import com.example.growtogether.dto.branch.response.AddNewBranchResponseDto;
import com.example.growtogether.dto.restaurant.response.AddRestaurantResponseDto;
import com.example.growtogether.entity.Restaurant;
import com.example.growtogether.entity.RestaurantBranch;
import org.springframework.stereotype.Component;

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


}
