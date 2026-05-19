package com.example.growtogether.repository;

import com.example.growtogether.entity.WaiterTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WaiterTableRepository extends JpaRepository<WaiterTable, Long> {
}
