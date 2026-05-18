package com.example.growtogether.constants;

import com.example.growtogether.exception.InvalidOrderStatusException;
import com.example.growtogether.exception.InvalidRestaurantTypeException;

public enum RestaurantType {

    STANDARD,
    LUXURY;

    public static RestaurantType toValue(String restaurantType){

        for(RestaurantType type : RestaurantType.values()){
            if(type.name().equalsIgnoreCase(restaurantType)){
                return type;
            }
        }
        throw new InvalidRestaurantTypeException(ExceptionMessages.INVALID_RESTAURANT_TYPE);
    }

}
