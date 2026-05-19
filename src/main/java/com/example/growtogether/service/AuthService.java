package com.example.growtogether.service;

import com.example.growtogether.dto.auth.request.RegenerateAccessTokenDto;
import com.example.growtogether.dto.auth.request.UserLoginRequestDto;
import com.example.growtogether.dto.auth.response.RegenerateAccessTokenResponse;
import com.example.growtogether.dto.auth.response.UserLoginResponseDto;

public interface AuthService {
    UserLoginResponseDto login(UserLoginRequestDto request);

    RegenerateAccessTokenResponse regenerateAccessToken(RegenerateAccessTokenDto refreshToken);
}
