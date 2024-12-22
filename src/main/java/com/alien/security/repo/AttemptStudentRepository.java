package com.alien.security.repo;

import com.alien.security.entity.AttemptStudent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AttemptStudentRepository extends JpaRepository<AttemptStudent, Long> {
    List<AttemptStudent> findByTestingGroupId(Long testingGroupId);
}
