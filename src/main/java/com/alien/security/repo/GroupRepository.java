package com.alien.security.repo;

import com.alien.security.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface GroupRepository extends JpaRepository<Group, Long>{
    List<Group> findByUser(UserModel user);
    Optional<Group> findByIdAndUser(Long id, UserModel user);
}

