package com.example.growtogether.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long inventoryId;

    @Column(nullable = false)
    private String billPhoto;

    @Column(nullable = false)
    private Double billAmount;

    @Column(nullable = false, updatable = false)
    private LocalDate purchaseDate;

    private String notes;

    @ManyToOne
    @JoinColumn(name = "branch_id", nullable = false)
    private RestaurantBranch branch;

    @ManyToOne
    @JoinColumn(name = "staff_id", nullable = false)
    private Staff staff;

}
