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
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long billId;

    @Column(nullable = false, updatable = false)
    private Date dateTime = new Date();

    @Column(nullable = false)
    private Double baseAmount;

    @Column(nullable = false)
    private Double taxAmount;

    @Column(nullable = false)
    private Double totalAmount;

    @OneToOne
    @JoinColumn(name = "table_session_id", nullable = false, unique = true)
    private TableSession tableSession;

    @ManyToOne
    @JoinColumn(name = "waiter_id", nullable = false)
    private Staff waiter;


}
