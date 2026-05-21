package com.example.growtogether.dto.restaurant.response;

import jakarta.persistence.Column;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetAllRestaurantBranch {

    private Long branchId;
    private String branchName;
    private String branchAddress;
    private Long contactNumber;
    private Integer numberOfTables;

}
