package com.example.growtogether.repository;

import com.example.growtogether.entity.Owner;
import com.example.growtogether.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OwnerRepository extends JpaRepository<Owner, Long> {

    Optional<Owner> findByUser(Users user);

}
