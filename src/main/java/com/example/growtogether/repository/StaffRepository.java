package com.example.growtogether.repository;

import com.example.growtogether.entity.RestaurantBranch;
import com.example.growtogether.entity.Staff;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Long> {
    Page<Staff> findAllByBranch(RestaurantBranch branch, Pageable pageable);
}
