package com.example.growtogether.dto.branch.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateManagerSalaryRequestDto {

    @NotNull
    private Long managerId;

    @NotNull
    @Min(value = 1, message = "Salary Should be grater than 0")
    private Double salary;
    
}
