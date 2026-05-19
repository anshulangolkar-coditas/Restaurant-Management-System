package com.example.growtogether.repository;

import com.example.growtogether.constants.InvitationStatus;
import com.example.growtogether.entity.Invitation;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface InvitationRepository extends JpaRepository<Invitation, Long> {

    List<Invitation> findByStatusAndExpiresOnBefore(InvitationStatus status, Date date);

    boolean existsByEmailId(@NotBlank String email);

    Invitation findByEmailId(@NotBlank String email);

    Optional<Invitation> findByUniqueKey(String uniqueKey);

}
