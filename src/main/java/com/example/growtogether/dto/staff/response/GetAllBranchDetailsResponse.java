package com.example.growtogether.dto.staff.response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetAllBranchDetailsResponse {

    private Long branchId;
    private String branchName;
    private String branchAddress;
    private Long contactNumber;
    private Integer numberOfTables;
    private GetManagerDetailsResponseDto manager;
    private List<GetStaffDetailsResponseDto> staffList;


}
