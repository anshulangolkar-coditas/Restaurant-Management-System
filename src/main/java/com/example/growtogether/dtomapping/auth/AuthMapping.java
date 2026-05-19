package com.example.growtogether.dtomapping.auth;

import com.example.growtogether.dto.auth.request.RegenerateAccessTokenDto;
import com.example.growtogether.dto.auth.request.RegisterUserRequestDto;
import com.example.growtogether.dto.auth.response.RegenerateAccessTokenResponse;
import com.example.growtogether.dto.auth.response.RegisterUserResponseDto;
import com.example.growtogether.dto.auth.response.UserLoginResponseDto;
import com.example.growtogether.entity.Users;
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

    public Users registerUserDtoToEntity(RegisterUserRequestDto request){

        return Users.builder()
                .fistName(request.getFirstName())
                .lastName(request.getLastName())
                .emailId(request.getEmail())
                .password(request.getPassword())
                .photoPath(request.getPhoto())
                .joinedDate(request.getJoinedDate())
                .build();
    }

    public RegisterUserResponseDto registerUserResponseDto(Users user){
        return RegisterUserResponseDto.builder()
                .userId(user.getUserId())
                .firstName(user.getFistName())
                .lastName(user.getLastName())
                .email(user.getEmailId())
                .roles(user.getRole())
                .photoPath(user.getPhotoPath())
                .dateJoined(user.getJoinedDate())
                .build();
    }

}
