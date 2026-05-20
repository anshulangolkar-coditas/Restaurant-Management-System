package com.example.growtogether.service;

import com.example.growtogether.dto.auth.request.RegenerateAccessTokenDto;
import com.example.growtogether.dto.auth.request.RegisterUserRequestDto;
import com.example.growtogether.dto.auth.request.UserLoginRequestDto;
import com.example.growtogether.dto.auth.response.RegenerateAccessTokenResponse;
import com.example.growtogether.dto.auth.response.RegisterUserResponseDto;
import com.example.growtogether.dto.auth.response.UserLoginResponseDto;
import com.example.growtogether.entity.Users;
import jakarta.validation.Valid;

public interface AuthService {
    UserLoginResponseDto login(UserLoginRequestDto request);

    RegenerateAccessTokenResponse regenerateAccessToken(RegenerateAccessTokenDto refreshToken);

    RegisterUserResponseDto registerUser(@Valid RegisterUserRequestDto request);

    String logoutUser(RegenerateAccessTokenDto refreshToken, Users user);
}
