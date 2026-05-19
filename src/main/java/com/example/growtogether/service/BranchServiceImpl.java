package com.example.growtogether.service;

import com.example.growtogether.constants.ExceptionMessages;
import com.example.growtogether.dto.branch.request.AddNewBranchRequestDto;
import com.example.growtogether.dto.branch.response.AddNewBranchResponseDto;
import com.example.growtogether.dto.restaurant.response.AddRestaurantResponseDto;
import com.example.growtogether.dtomapping.branch.BranchMapping;
import com.example.growtogether.dtomapping.restaurant.RestaurantMapping;
import com.example.growtogether.entity.Restaurant;
import com.example.growtogether.entity.RestaurantBranch;
import com.example.growtogether.entity.Users;
import com.example.growtogether.exception.RestaurantNotFoundException;
import com.example.growtogether.repository.RestaurantBranchRepository;
import com.example.growtogether.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class BranchServiceImpl  implements BranchService{

    private final RestaurantBranchRepository restaurantBranchRepository;
    private final RestaurantRepository restaurantRepository;
    private final BranchMapping branchMapping;
    private final RestaurantMapping restaurantMapping;

    @Override
    public AddNewBranchResponseDto addBranch(AddNewBranchRequestDto request, Users user) {

        Restaurant restaurant = restaurantRepository.findById(request.getRestaurantId())
                .orElseThrow(() -> new RestaurantNotFoundException(ExceptionMessages.RESTAURANT_NOT_FOUND));

        AddRestaurantResponseDto restaurantDto = restaurantMapping.EntityToDto(restaurant);

        RestaurantBranch branch = restaurantBranchRepository.save(branchMapping.dtoToEntity(request, restaurant));

        return branchMapping.entityToDto(branch,restaurantDto);
    }
}
