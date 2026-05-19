package com.example.growtogether.dto.branch.response;

import com.example.growtogether.dto.restaurant.response.AddRestaurantResponseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddBranchResponseDto {

    AddRestaurantResponseDto restaurant = new AddRestaurantResponseDto();

    


}
