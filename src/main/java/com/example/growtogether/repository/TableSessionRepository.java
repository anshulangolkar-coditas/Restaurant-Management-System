package com.example.growtogether.repository;

import com.example.growtogether.entity.TableSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TableSessionRepository extends JpaRepository<TableSession, Long> {
}
