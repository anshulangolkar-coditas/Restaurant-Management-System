package com.example.growtogether.repository;

import com.example.growtogether.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users, Long> {

    boolean existsByEmailId(String email);

    Optional<Users> findByEmailId(String email);


}
