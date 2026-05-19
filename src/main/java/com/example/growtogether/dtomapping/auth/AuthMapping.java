package com.example.growtogether.dtomapping.auth;

import com.example.growtogether.dto.auth.request.RegenerateAccessTokenDto;
import com.example.growtogether.dto.auth.response.RegenerateAccessTokenResponse;
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

    public String toRefreshToken(RegenerateAccessTokenDto request){
        return request.getRefreshToken();
    }

    public RegenerateAccessTokenResponse responseRefreshToken(String accessToken, String refreshToken){

        return RegenerateAccessTokenResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

}
