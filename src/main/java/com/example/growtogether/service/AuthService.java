package com.example.growtogether.service;

import com.example.growtogether.dto.auth.request.UserLoginRequestDto;
import com.example.growtogether.dto.auth.response.UserLoginResponseDto;

public interface AuthService {
    UserLoginResponseDto login(UserLoginRequestDto request);

    String regenerateAccessToken(String refreshToken, Long userId);
}
