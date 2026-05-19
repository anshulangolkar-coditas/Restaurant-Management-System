package com.example.growtogether.dto.restaurant.response;

import com.example.growtogether.constants.RestaurantType;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddRestaurantResponseDto {

    private Long restaurantId;
    private String restaurantName;
    private RestaurantType type;


}
