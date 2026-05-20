package com.example.growtogether.controller;

import com.example.growtogether.dto.auth.request.RegenerateAccessTokenDto;
import com.example.growtogether.dto.auth.request.RegisterUserRequestDto;
import com.example.growtogether.dto.auth.request.UserLoginRequestDto;
import com.example.growtogether.dto.auth.response.RegenerateAccessTokenResponse;
import com.example.growtogether.dto.auth.response.RegisterUserResponseDto;
import com.example.growtogether.dto.auth.response.UserLoginResponseDto;
import com.example.growtogether.entity.Users;
import com.example.growtogether.response.ApplicationResponse;
import com.example.growtogether.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @PreAuthorize("hasAnyRole('ADMIN','OWNER','MANAGER', 'STAFF_WAITER','STAFF_KITCHEN')")
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

    @PostMapping("/register")
    public ResponseEntity<ApplicationResponse<RegisterUserResponseDto>> registerUser(@Valid @RequestBody RegisterUserRequestDto request){

        RegisterUserResponseDto userDetails = authService.registerUser(request);

        ApplicationResponse<RegisterUserResponseDto> response = new ApplicationResponse<>(
                HttpStatus.CREATED.value(),
                "User Added Successfully",
                userDetails
        );
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyRole('ADMIN','OWNER','MANAGER', 'STAFF_WAITER','STAFF_KITCHEN')")
    @PatchMapping("/logout")
    public ResponseEntity<ApplicationResponse<String>> logoutUser(@Valid @RequestBody RegenerateAccessTokenDto refreshToken ,@AuthenticationPrincipal Users user){
        String userDetails = authService.logoutUser(refreshToken, user);

        ApplicationResponse<String> response = new ApplicationResponse<>(
                HttpStatus.CREATED.value(),
                "User Logged Out Successfully",
                userDetails
        );
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }






}
