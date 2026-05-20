package com.example.growtogether.dto.branch.response;

import com.example.growtogether.constants.Role;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateManagerSalaryResponseDto {

    private Long staffId;
    private String fistName;
    private String lastName;
    private Set<Role> role;
    private Double salary;

}
