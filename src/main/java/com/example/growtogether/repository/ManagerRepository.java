package com.example.growtogether.repository;

import com.example.growtogether.entity.Manager;
import com.example.growtogether.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ManagerRepository extends JpaRepository<Manager, Long> {
    Optional<Manager> findByUser(Users user);
}
