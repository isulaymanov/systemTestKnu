package com.alien.security.repo;

import com.alien.security.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TestingRepository extends JpaRepository<Testing, Long>{
    List<Testing> findByUser(UserModel user);
    Optional<Testing> findByIdAndUser(Long id, UserModel user);
}
