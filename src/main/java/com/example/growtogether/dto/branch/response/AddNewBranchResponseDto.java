package com.example.growtogether.dto.branch.response;

import com.example.growtogether.dto.restaurant.response.AddRestaurantResponseDto;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddNewBranchResponseDto {

    private Long branchId;
    private String branchName;
    private String address;
    private Long number;
    private Integer numberOfTables;

    AddRestaurantResponseDto restaurant = new AddRestaurantResponseDto();


}
