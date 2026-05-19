package com.example.growtogether.repository;

import com.example.growtogether.entity.Owner;
import com.example.growtogether.entity.OwnerRestaurant;
import com.example.growtogether.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OwnerRestaurantRepository extends JpaRepository<OwnerRestaurant, Long> {
    OwnerRestaurant findByOwnerAndRestaurant(Owner owner, Restaurant restaurant);
}
