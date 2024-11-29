package com.alien.security.repo;

import com.alien.security.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FacultyRepository extends JpaRepository<Faculty, Long> {
    List<Faculty> findByUser(UserModel user);
    Optional<Faculty> findByIdAndUser(Long id, UserModel user);

}
