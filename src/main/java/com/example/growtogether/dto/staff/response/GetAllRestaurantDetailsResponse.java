package com.example.growtogether.dto.staff.response;

import com.example.growtogether.constants.RestaurantType;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetAllRestaurantDetailsResponse implements GetAllGenericResponse {

    private Long restaurantId;
    private String restaurantName;
    private RestaurantType type;
    private List<GetAllBranchDetailsResponse> branches;

}
