package com.example.growtogether.dto.auth.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserLoginResponseDto {

    private String accessToken;
    private String refreshToken;

}
