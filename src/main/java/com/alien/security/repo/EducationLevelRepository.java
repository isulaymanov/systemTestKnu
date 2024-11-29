package com.alien.security.repo;

import com.alien.security.entity.EducationLevel;
import com.alien.security.entity.Faculty;
import com.alien.security.entity.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository

public interface EducationLevelRepository extends JpaRepository<EducationLevel, Long> {

}
