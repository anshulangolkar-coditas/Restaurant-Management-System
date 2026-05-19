package com.example.growtogether.repository;

import com.example.growtogether.entity.DishCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DishCategoryRepository extends JpaRepository<DishCategory,Long> {
}
