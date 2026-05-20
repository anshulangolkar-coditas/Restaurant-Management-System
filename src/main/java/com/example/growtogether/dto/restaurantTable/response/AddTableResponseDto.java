package com.example.growtogether.dto.restaurantTable.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddTableResponseDto {

    private Long tableId;
    private String tableName;
    private String restaurantName;
    private String branchName;

}
