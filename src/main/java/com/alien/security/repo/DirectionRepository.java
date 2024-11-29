package com.alien.security.repo;
import com.alien.security.entity.Direction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface DirectionRepository extends JpaRepository<Direction, Long> {
}
