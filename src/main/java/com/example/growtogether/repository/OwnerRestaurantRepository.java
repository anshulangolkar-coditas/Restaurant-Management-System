package com.example.growtogether.repository;

import com.example.growtogether.entity.OwnerRestaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OwnerRestaurantRepository extends JpaRepository<OwnerRestaurant, Long> {
}
