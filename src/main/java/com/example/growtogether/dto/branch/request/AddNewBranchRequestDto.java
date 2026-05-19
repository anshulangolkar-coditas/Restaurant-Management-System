package com.example.growtogether.dto.branch.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddNewBranchRequestDto {

    @NotNull
    private Long restaurantId;

    @NotBlank
    private String branchName;

    @NotBlank
    private String branchAddress;

    @NotNull
    private Long number;

    @NotNull
    private Integer numberOfTables;


}
