package com.example.growtogether.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RefreshToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long refreshTokenId;

    @Column(nullable = false)
    private String token;

    @Column(nullable = false, updatable = false)
    private Date createdAt = new Date();

    @Column(nullable = false, updatable = false)
    private Date expiresAt;

    @Column(nullable = false)
    private Boolean blackListed = false;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Users user;

}
