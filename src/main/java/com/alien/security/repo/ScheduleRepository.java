package com.alien.security.repo;

import com.alien.security.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;


@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    List<Schedule> findByDisciplineId(Long disciplineId);
    Optional<Schedule> findFirstByGroupIdOrderByDateAsc(Long groupId);
    List<Schedule> findByGroupId(Long groupId);
    /**
     * Находит расписание по ID группы и дате.
     *
     * @param groupId ID группы.
     * @param date Дата занятия.
     * @return Optional с найденным расписанием.
     */
    Optional<Schedule> findByGroupIdAndDate(Long groupId, String date);

}
