package com.example.growtogether.service;

import com.example.growtogether.constants.ExceptionMessages;
import com.example.growtogether.dto.restaurant.request.AddRestaurantRequestDto;
import com.example.growtogether.dto.restaurant.request.DeleteRestaurantRequestDto;
import com.example.growtogether.dto.restaurant.response.AddRestaurantResponseDto;
import com.example.growtogether.dtomapping.restaurant.RestaurantMapping;
import com.example.growtogether.entity.Owner;
import com.example.growtogether.entity.OwnerRestaurant;
import com.example.growtogether.entity.Restaurant;
import com.example.growtogether.entity.Users;
import com.example.growtogether.exception.RestaurantBranchesExistsException;
import com.example.growtogether.exception.RestaurantNotFoundException;
import com.example.growtogether.exception.UserNotFoundException;
import com.example.growtogether.repository.OwnerRepository;
import com.example.growtogether.repository.OwnerRestaurantRepository;
import com.example.growtogether.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final OwnerRestaurantRepository ownerRestaurantRepository;
    private final OwnerRepository ownerRepository;
    private final RestaurantMapping restaurantMapping;

    @Override
    public AddRestaurantResponseDto addRestaurant(AddRestaurantRequestDto request, Users user) {

        Owner owner = ownerRepository.findById(user.getUserId())
                .orElseThrow(() -> new UserNotFoundException(ExceptionMessages.USER_NOT_FOUND));

        Restaurant savedRestaurant = restaurantRepository.save(restaurantMapping.dtoToEntity(request));

        ownerRestaurantRepository.save(OwnerRestaurant.builder()
                .restaurant(savedRestaurant)
                .owner(owner)
                .build());

        return restaurantMapping.EntityToDto(savedRestaurant);
    }

    @Override
    public String deleteRestaurant(DeleteRestaurantRequestDto request, Users user) {

        Restaurant restaurant = restaurantRepository.findById(request.getRestaurantId())
                .orElseThrow(() -> new RestaurantNotFoundException(ExceptionMessages.RESTAURANT_NOT_FOUND));

        if(!restaurant.getBranches().isEmpty()){
            throw new RestaurantBranchesExistsException(ExceptionMessages.RESTAURANT_BRANCH_EXISTS);
        }

        Owner owner = ownerRepository.findById(user.getUserId())
                .orElseThrow(() -> new UserNotFoundException(ExceptionMessages.USER_NOT_FOUND));

        OwnerRestaurant ownerRestaurant = ownerRestaurantRepository.findByOwnerAndRestaurant(owner, restaurant);

        ownerRestaurantRepository.delete(ownerRestaurant);
        restaurantRepository.delete(restaurant);

        return "Deleted Successfully";
    }
}
