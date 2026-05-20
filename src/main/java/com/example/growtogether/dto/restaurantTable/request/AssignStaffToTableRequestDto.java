package com.example.growtogether.dto.restaurantTable.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AssignStaffToTableRequestDto {

    @NotNull
    private Long staffId;

    @NotNull
    private Long tableId;



}
