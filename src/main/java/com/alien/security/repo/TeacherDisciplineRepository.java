package com.alien.security.repo;

import com.alien.security.entity.TeacherDiscipline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import org.springframework.data.jpa.repository.EntityGraph;
import com.alien.security.dto.*;
import org.springframework.data.jpa.repository.Query;


@Repository
public interface TeacherDisciplineRepository extends JpaRepository<TeacherDiscipline, Long> {

    @Query("SELECT new com.alien.security.dto.TeacherDisciplineDTO(td.id, " +
            "u.id, u.name, u.lastName, u.middleName, u.username, u.role, " +
            "d.id, d.name, d.code, d.description, " +
            "dep.id, dep.name, " +
            "dir.id, dir.name) " +
            "FROM TeacherDiscipline td " +
            "JOIN td.user u " +
            "JOIN td.discipline d " +
            "JOIN d.department dep " +
            "JOIN d.direction dir " +
            "ORDER BY td.id ASC")
    List<TeacherDisciplineDTO> findAllTeacherDisciplineDetails();



    List<TeacherDiscipline> findByUserId(Integer userId);
  // Метод для поиска по userId

}
