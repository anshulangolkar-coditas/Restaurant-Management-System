package com.example.growtogether.entity;

import com.example.growtogether.constants.FoodType;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RestaurantMenu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long menuId;

    @Column(nullable = false)
    private String dishName;

    private String dishDescription;

    @Column(nullable = false)
    private Double dishPrice;

    @Column(nullable = false)
    private String ingredients;

    private String allergyInfo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    FoodType foodType;

    @OneToMany(mappedBy = "menuItem")
    private List<OrderItem> orderItems;

    @ManyToOne
    @JoinColumn(name = "branch_id", nullable = false)
    private RestaurantBranch branch;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private DishCategory category;

}
