package com.example.growtogether.repository;

import com.example.growtogether.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {

    boolean existsByEmailId(String email);

    Optional<Users> findByEmailId(String email);


}
