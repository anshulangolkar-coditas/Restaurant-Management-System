package com.example.growtogether.dto.restaurant.response;

import com.example.growtogether.constants.RestaurantType;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetAllRestaurantsResponse {

    private Long restaurantId;
    private String restaurantName;
    private RestaurantType type;
    private List<GetAllRestaurantBranch> branches;

}
