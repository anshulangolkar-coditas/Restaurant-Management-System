package com.example.growtogether.entity;

import com.example.growtogether.constants.RestaurantType;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long restaurantId;

    @Column(nullable = false, unique = true)
    private String restaurantName;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    RestaurantType restaurantType;

    @OneToMany(mappedBy = "restaurant")
    private List<OwnerRestaurant> ownerRestaurants;

    @OneToMany(mappedBy = "restaurant")
    private List<RestaurantBranch> branches;

}
