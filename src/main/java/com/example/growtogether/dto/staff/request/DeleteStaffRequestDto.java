package com.example.growtogether.dto.staff.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DeleteStaffRequestDto {

    @NotNull
    private Long branchId;

    @NotNull
    private Long staffId;


}
