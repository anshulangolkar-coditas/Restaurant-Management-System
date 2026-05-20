package com.example.growtogether.repository;

import com.example.growtogether.entity.RefreshToken;
import com.example.growtogether.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

    Optional<RefreshToken> findByToken(String token);

    RefreshToken findByUser(Users user);
}
