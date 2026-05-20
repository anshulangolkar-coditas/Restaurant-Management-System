package com.example.growtogether.dto.restaurantTable.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AssignStaffToTableResponseDto {

    private Long waiterId;
    private String waiterFirstName;
    private String waiterLastName;
    private Long restaurantId;
    private String restaurantName;
    private Long branchId;
    private String branchName;
    private Long tableId;
    private String tableName;

}
