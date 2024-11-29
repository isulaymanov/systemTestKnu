package com.alien.security.repo;

import com.alien.security.entity.Grades;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface GradesRepository extends JpaRepository<Grades, Long> {
    List<Grades> findByStudentId(Long studentId);

}
