package com.example.growtogether.util;

import com.example.growtogether.constants.ExceptionMessages;
import com.example.growtogether.entity.RefreshToken;
import com.example.growtogether.entity.Users;
import com.example.growtogether.exception.RefreshTokenExpiredException;
import com.example.growtogether.repository.RefreshTokenRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class JwtUtil {


    private final RefreshTokenRepository refreshTokenRepository;

    private final String SECRET_KEY = "thishkajdshkjashdkjahsdkjhaksjdhkjahsdkhkjadshkjahskdjhakshdsjdva";
    private final Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

    private final Long REFRESH_TOKEN_EXPIRY = 1000L * 60L * 60L * 24;

    private final Long EXPIRATION_TIME = 1000L * 60L * 10L;


    public String generateToken(String username){
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+ EXPIRATION_TIME))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public Claims extractClaims(String token){
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String extractUsername(String token){
        return extractClaims(token).getSubject();
    }

    public boolean isTokenExpired(String token){
        return extractClaims(token).getExpiration().before(new Date());
    }

    public boolean isValidToken(String token, String username){
        return extractUsername(token).equals(username) && !isTokenExpired(token);
    }

    public String generateRefreshToken(Users user){

        String token = UUID.randomUUID().toString();

        RefreshToken refreshToken = RefreshToken.builder()
                .token(token)
                .expiresAt(new Date(System.currentTimeMillis() + REFRESH_TOKEN_EXPIRY))
                .user(user)
                .build();

        RefreshToken savedRefreshToken = refreshTokenRepository.save(refreshToken);

        return savedRefreshToken.getToken();
    }

    public boolean isRefreshTokenValid(RefreshToken refreshToken){

        if(refreshToken.getExpiresAt().before(new Date())){
            throw new RefreshTokenExpiredException(ExceptionMessages.REFRESH_TOKEN_EXPIRED);
        }
        return true;
    }

}
