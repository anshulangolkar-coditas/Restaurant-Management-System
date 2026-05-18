package com.example.growtogether.constants;

import com.example.growtogether.exception.InvalidFoodTypeException;

public enum FoodType {

    VEG,
    NON_VEG;

    public static FoodType toValue(String foodType){

        for(FoodType food : FoodType.values()){
            if(food.name().equalsIgnoreCase(foodType)){
                return food;
            }
        }
        throw new InvalidFoodTypeException(ExceptionMessages.INVALID_FOOD_TYPE);
    }

}
