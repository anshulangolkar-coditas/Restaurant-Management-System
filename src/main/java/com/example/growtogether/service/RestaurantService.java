package com.example.growtogether.service;

import com.example.growtogether.dto.restaurant.request.AddRestaurantRequestDto;
import com.example.growtogether.dto.restaurant.request.DeleteRestaurantRequestDto;
import com.example.growtogether.dto.restaurant.response.AddRestaurantResponseDto;
import com.example.growtogether.entity.Users;
import jakarta.validation.Valid;

import java.util.List;

public interface RestaurantService {
    AddRestaurantResponseDto addRestaurant(@Valid AddRestaurantRequestDto request, Users user);

    String deleteRestaurant(@Valid DeleteRestaurantRequestDto request, Users user);

    List<AddRestaurantResponseDto> getAllRestaurants(int page, Users user);
}
