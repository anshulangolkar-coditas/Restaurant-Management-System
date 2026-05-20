package com.example.growtogether.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RestaurantTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tableId;

    @ManyToOne
    @JoinColumn(name = "branch_id", nullable = false)
    private RestaurantBranch branch;

    @Column(nullable = false)
    private String tableName;

    @Column(nullable = false, updatable = false)
    @Builder.Default
    private Date assignedAt = new Date();

    private Date finishedAt;

    @OneToMany(mappedBy = "table")
    private List<WaiterTable> waiterAssignment;

    @OneToMany(mappedBy = "table")
    private List<Orders> orders;

    @OneToMany(mappedBy = "table")
    private List<TableSession> tableSession;

}
