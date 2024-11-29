package com.alien.security.repo;
import com.alien.security.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface GroupRepository extends JpaRepository<Group, Long> {
}
