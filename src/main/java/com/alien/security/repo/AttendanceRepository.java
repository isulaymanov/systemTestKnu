package com.alien.security.repo;

import com.alien.security.entity.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
    List<Attendance> findByStudentIdAndDate(Long studentId, String date);
    List<Attendance> findByStudentId(Long studentId);

}
