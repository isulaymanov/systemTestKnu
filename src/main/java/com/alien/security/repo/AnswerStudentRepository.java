package com.alien.security.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.alien.security.entity.AnswerStudent;
import java.util.List;

@Repository
public interface AnswerStudentRepository extends JpaRepository<AnswerStudent, Long> {
    List<AnswerStudent> findByAttemptStudentId(Long attemptStudentId);
}

