package com.example.growtogether.dtomapping.restaurant;

import com.example.growtogether.constants.RestaurantType;
import com.example.growtogether.dto.restaurant.request.AddRestaurantRequestDto;
import com.example.growtogether.dto.restaurant.response.AddRestaurantResponseDto;
import com.example.growtogether.dto.restaurant.response.GetAllRestaurantBranch;
import com.example.growtogether.dto.restaurant.response.GetAllRestaurantsResponse;
import com.example.growtogether.entity.Restaurant;
import com.example.growtogether.entity.RestaurantBranch;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RestaurantMapping {

    public Restaurant dtoToEntity(AddRestaurantRequestDto request){

        RestaurantType type = RestaurantType.toValue(request.getRestaurantType());

        return Restaurant.builder()
                .restaurantName(request.getRestaurantName())
                .restaurantType(type)
                .build();
    }

    public AddRestaurantResponseDto entityToDto(Restaurant restaurant){
        return AddRestaurantResponseDto.builder()
                .restaurantId(restaurant.getRestaurantId())
                .restaurantName(restaurant.getRestaurantName())
                .type(restaurant.getRestaurantType())
                .build();
    }


    public GetAllRestaurantBranch getAllRestaurantBranchToDto(RestaurantBranch branch){
        return GetAllRestaurantBranch.builder()
                .branchId(branch.getBranchId())
                .branchName(branch.getBranchName())
                .branchAddress(branch.getBranchAddress())
                .contactNumber(branch.getContactNumber())
                .numberOfTables(branch.getNumberOfTables())
                .build();
    }


    public GetAllRestaurantsResponse getAllRestaurantsResponseToDto(Restaurant restaurant){
        return GetAllRestaurantsResponse.builder()
                .restaurantId(restaurant.getRestaurantId())
                .restaurantName(restaurant.getRestaurantName())
                .type(restaurant.getRestaurantType())
                .branches(restaurant.getBranches().stream().map(this::getAllRestaurantBranchToDto).toList())
                .build();
    }


    public List<GetAllRestaurantsResponse> listOfRestaurants(Page<Restaurant> restaurantList){
        return restaurantList.stream()
                .map(this::getAllRestaurantsResponseToDto).toList();
    }

    public List<GetAllRestaurantsResponse> listOfRestaurants(List<Restaurant> restaurantList){
        return restaurantList.stream()
                .map(this::getAllRestaurantsResponseToDto).toList();
    }
}
