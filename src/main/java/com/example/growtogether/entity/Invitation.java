package com.example.growtogether.entity;

import com.example.growtogether.constants.InvitationStatus;
import com.example.growtogether.constants.Role;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Invitation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long invitationId;

    @Column(nullable = false, unique = true)
    private String emailId;

    @Column(nullable = false)
    private String fullName;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Column(updatable = false)
    private String uniqueKey;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Builder.Default
    private InvitationStatus status = InvitationStatus.PENDING;

    @Column(updatable = false, nullable = false)
    @Builder.Default
    private final Date sentOn = new Date();

    @Column(updatable = false, nullable = false)
    @Builder.Default
    private final Date expiresOn = new Date(System.currentTimeMillis() + 1000L * 60L * 10L);

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sent_by_user", nullable = false)
    private Users sentBy;

}
