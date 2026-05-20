package com.example.growtogether.service;

import com.example.growtogether.dto.restaurantTable.request.AddRestaurantTableRequestDto;
import com.example.growtogether.dto.restaurantTable.request.AssignStaffToTableRequestDto;
import com.example.growtogether.dto.restaurantTable.response.AddTableResponseDto;
import com.example.growtogether.dto.restaurantTable.response.AssignStaffToTableResponseDto;
import com.example.growtogether.entity.Users;
import jakarta.validation.Valid;

public interface RestaurantTableService {


    AddTableResponseDto addTable(@Valid AddRestaurantTableRequestDto request, Users user);

    AssignStaffToTableResponseDto assignStaffToTable(@Valid AssignStaffToTableRequestDto request, Users user);
}
