package com.example.growtogether.entity;

import com.example.growtogether.constants.Role;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Staff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long staffId;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private Users user;

    @ManyToOne
    @JoinColumn(name = "branch_id", nullable = false)
    private RestaurantBranch branch;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    private Double salary;

    @OneToMany(mappedBy = "staff")
    private List<WaiterTable> tableAssignment;

    @OneToMany(mappedBy = "staff")
    private List<Orders> orders;

    @OneToMany(mappedBy = "staff")
    private List<Inventory> inventoryLogs;

}
