package com.example.growtogether.dto.restaurantTable.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
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
