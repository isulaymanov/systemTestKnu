package com.alien.security.repo;

import com.alien.security.entity.Discipline;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface DisciplineRepository extends JpaRepository<Discipline, Long> {
}
