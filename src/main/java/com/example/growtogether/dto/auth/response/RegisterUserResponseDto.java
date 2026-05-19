package com.example.growtogether.dto.auth.response;

import com.example.growtogether.constants.Role;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterUserResponseDto {


    private Long userId;
    private String firstName;
    private String lastName;
    private String email;
    private Set<Role> roles;
    private String photoPath;
    private LocalDate dateJoined;


}
