package com.example.growtogether.dto.staff.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetAllStaffResponseDto {

    private Long staffId;
    private String fistName;
    private String lastName;
    private Double salary;
    private Long restaurantId;
    private String restaurantName;
    private Long branchId;
    private String branchName;

}
