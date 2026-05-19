package com.example.growtogether.repository;

import com.example.growtogether.entity.RestaurantMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantMenuRepository extends JpaRepository<RestaurantMenu, Long> {
}
