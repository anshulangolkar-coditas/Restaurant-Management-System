package com.example.growtogether.dtomapping.table;

import com.example.growtogether.dto.restaurantTable.request.AssignStaffToTableRequestDto;
import com.example.growtogether.dto.restaurantTable.response.AddTableResponseDto;
import com.example.growtogether.dto.restaurantTable.response.AssignStaffToTableResponseDto;
import com.example.growtogether.entity.*;
import org.springframework.stereotype.Component;

@Component
public class RestaurantTableMapping {

    public AddTableResponseDto entityToDto(Restaurant restaurant, RestaurantBranch branch, RestaurantTable table){

        return AddTableResponseDto.builder()
                .tableId(table.getTableId())
                .tableName(table.getTableName())
                .restaurantName(restaurant.getRestaurantName())
                .branchName(branch.getBranchName())
                .build();
    }

    public WaiterTable dtoToEntity(Staff staff, RestaurantTable table){

        return WaiterTable.builder()
                .staff(staff)
                .table(table)
                .build();
    }

    public AssignStaffToTableResponseDto assignStaffResponse(Staff staff, Users userStaff, Restaurant restaurant, RestaurantBranch branch, RestaurantTable table){

        return AssignStaffToTableResponseDto.builder()
                .waiterId(staff.getStaffId())
                .waiterFirstName(userStaff.getFistName())
                .waiterLastName(userStaff.getLastName())
                .restaurantId(restaurant.getRestaurantId())
                .restaurantName(restaurant.getRestaurantName())
                .branchId(branch.getBranchId())
                .branchName(branch.getBranchName())
                .tableId(table.getTableId())
                .tableName(table.getTableName())
                .build();
    }


}
