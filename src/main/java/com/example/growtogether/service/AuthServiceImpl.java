package com.example.growtogether.service;

import com.example.growtogether.constants.ExceptionMessages;
import com.example.growtogether.dto.auth.request.RegenerateAccessTokenDto;
import com.example.growtogether.dto.auth.request.UserLoginRequestDto;
import com.example.growtogether.dto.auth.response.RegenerateAccessTokenResponse;
import com.example.growtogether.dto.auth.response.UserLoginResponseDto;
import com.example.growtogether.dtomapping.auth.AuthMapping;
import com.example.growtogether.entity.RefreshToken;
import com.example.growtogether.entity.Users;
import com.example.growtogether.exception.PasswordNotMatchedException;
import com.example.growtogether.exception.RefreshTokenNotValidException;
import com.example.growtogether.exception.UserNotFoundException;
import com.example.growtogether.repository.RefreshTokenRepository;
import com.example.growtogether.repository.UsersRepository;
import com.example.growtogether.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{

    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthMapping authMapping;
    private final RefreshTokenRepository refreshTokenRepository;
    private final AuthenticationManager authenticationManager;

    @Override
    public UserLoginResponseDto login(UserLoginRequestDto request) {

        Authentication authentication = null;

        try {
            authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        } catch (UserNotFoundException e) {
            throw new UserNotFoundException(ExceptionMessages.USER_NOT_FOUND);
        }

        Users user = (Users) authentication.getPrincipal();

        String accessToken = jwtUtil.generateToken(request.getEmail());
        String refreshToken = jwtUtil.generateRefreshToken(user);

        return authMapping.loginToDto(accessToken,refreshToken);
    }

    @Override
    public RegenerateAccessTokenResponse regenerateAccessToken(RegenerateAccessTokenDto refreshToken) {

        String accessToken = null;

        String refresh = authMapping.toRefreshToken(refreshToken);

        RefreshToken token = refreshTokenRepository.findByToken(refresh)
                .orElseThrow(() -> new RefreshTokenNotValidException(ExceptionMessages.REFRESH_TOKEN_NOT_VALID));

        Users user = token.getUser();

        if(jwtUtil.isRefreshTokenValid(token)){
            accessToken = jwtUtil.generateToken(user.getEmailId());
        }
        return authMapping.responseRefreshToken(accessToken,token.getToken());
    }



}
