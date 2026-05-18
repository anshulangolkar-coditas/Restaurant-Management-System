package com.example.growtogether.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RestaurantBranch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long branchId;

    @Column(nullable = false)
    private String branchName;

    @Column(nullable = false)
    private String branchAddress;

    @Column(nullable = false)
    private Long contactNumber;

    @Column(nullable = false,updatable = false)
    private Integer numberOfTables;

    @ManyToOne
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;

    @OneToOne
    @JoinColumn(name = "manager_id")
    private Manager manager;

    @OneToMany(mappedBy = "branch")
    private List<Staff> staffList;

    @OneToMany(mappedBy = "branch")
    private List<RestaurantTable> tables;

    @OneToMany(mappedBy = "branch")
    private List<RestaurantMenu> menuItems;

    @OneToMany(mappedBy = "branch")
    private List<Inventory> inventory;

}
