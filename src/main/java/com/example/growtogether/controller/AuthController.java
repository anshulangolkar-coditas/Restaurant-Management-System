package com.example.growtogether.controller;

import com.example.growtogether.dto.auth.request.RegenerateAccessTokenDto;
import com.example.growtogether.dto.auth.request.UserLoginRequestDto;
import com.example.growtogether.dto.auth.response.RegenerateAccessTokenResponse;
import com.example.growtogether.dto.auth.response.UserLoginResponseDto;
import com.example.growtogether.entity.Users;
import com.example.growtogether.response.ApplicationResponse;
import com.example.growtogether.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<ApplicationResponse<UserLoginResponseDto>> login(@Valid @RequestBody UserLoginRequestDto request){

        UserLoginResponseDto loginData = authService.login(request);

        ApplicationResponse<UserLoginResponseDto> response = new ApplicationResponse<>(
                HttpStatus.OK.value(),
                "Logged In Successfully",
                loginData
        );

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/refresh")
    public ResponseEntity<ApplicationResponse<RegenerateAccessTokenResponse>> regenerateAccessToken(@Valid @RequestBody RegenerateAccessTokenDto refreshToken){

        RegenerateAccessTokenResponse token = authService.regenerateAccessToken(refreshToken);

        ApplicationResponse<RegenerateAccessTokenResponse> response = new ApplicationResponse<>(
                HttpStatus.OK.value(),
                "Access Token Generated Successfully",
                token
        );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }






}
