package com.example.growtogether.dto.staff.response;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetManagerDetailsResponseDto {

    private Long managerId;
    private String managerName;
    private String email;
    private Double salary;
    private LocalDate joinedDate;

}
