package com.example.growtogether.dto.staff.response;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetStaffDetailsResponseDto {

    private Long staffId;
    private String staffName;
    private String email;
    private Double salary;
    private LocalDate joinedDate;

}
