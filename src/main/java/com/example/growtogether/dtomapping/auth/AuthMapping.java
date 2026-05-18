package com.example.growtogether.dtomapping.auth;

import com.example.growtogether.dto.auth.response.UserLoginResponseDto;
import org.springframework.stereotype.Component;

@Component
public class AuthMapping {

    public UserLoginResponseDto loginToDto(String accessToken, String refreshToken){

        return UserLoginResponseDto.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();

    }


}
