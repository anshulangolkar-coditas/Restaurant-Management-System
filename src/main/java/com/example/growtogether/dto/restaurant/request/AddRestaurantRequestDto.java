package com.example.growtogether.dto.restaurant.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddRestaurantRequestDto {

    @NotBlank
    private String restaurantName;

    @NotBlank
    private String restaurantType;


}
