package com.example.growtogether.dtomapping.restaurant;

import com.example.growtogether.constants.RestaurantType;
import com.example.growtogether.dto.restaurant.request.AddRestaurantRequestDto;
import com.example.growtogether.dto.restaurant.response.AddRestaurantResponseDto;
import com.example.growtogether.entity.Restaurant;
import com.example.growtogether.entity.Users;
import org.springframework.stereotype.Component;

@Component
public class RestaurantMapping {

    public Restaurant dtoToEntity(AddRestaurantRequestDto request){

        RestaurantType type = RestaurantType.toValue(request.getRestaurantType());

        return Restaurant.builder()
                .restaurantName(request.getRestaurantName())
                .restaurantType(type)
                .build();
    }

    public AddRestaurantResponseDto EntityToDto(Restaurant restaurant){
        return AddRestaurantResponseDto.builder()
                .restaurantId(restaurant.getRestaurantId())
                .restaurantName(restaurant.getRestaurantName())
                .type(restaurant.getRestaurantType())
                .build();
    }

}
