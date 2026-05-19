package com.example.growtogether.repository;

import com.example.growtogether.entity.RestaurantBranch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantBranchRepository extends JpaRepository<RestaurantBranch, Long> {
}
