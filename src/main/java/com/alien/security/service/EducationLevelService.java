package com.alien.security.service;
import java.util.stream.Collectors;

import com.alien.security.dto.*;
import com.alien.security.entity.EducationLevel;
import com.alien.security.repo.EducationLevelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.Optional;
import com.alien.security.repo.*;
import java.util.List;
import java.util.Optional;
import com.alien.security.entity.*;
import jakarta.persistence.EntityNotFoundException;

@Service
public class EducationLevelService {

    private final EducationLevelRepository educationLevelRepository;
    private final FacultyRepository facultyRepository; // Добавляем репозиторий для Faculty

    @Autowired
    public EducationLevelService(EducationLevelRepository educationLevelRepository,FacultyRepository facultyRepository) {
        this.educationLevelRepository = educationLevelRepository;
        this.facultyRepository = facultyRepository;

    }

    public EducationLevel createEducationLevel(EducationLevel educationLevel) {
        return educationLevelRepository.save(educationLevel);
    }


    public List<EducationLevel> getAllEducationLevels() {
        return educationLevelRepository.findAll();  // Получаем все уровни образования
    }

    public EducationLevel updateEducationLevel(Long id, EducationLevel updatedEducationLevel) {
        EducationLevel existingEducationLevel = educationLevelRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Education level not found"));

        existingEducationLevel.setName(updatedEducationLevel.getName());
        existingEducationLevel.setFaculty(updatedEducationLevel.getFaculty());

        return educationLevelRepository.save(existingEducationLevel);
    }

    public void deleteEducationLevel(Long id) {
        if (educationLevelRepository.existsById(id)) {
            educationLevelRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Education Level not found with id " + id);
        }
    }

    public class ResourceNotFoundException extends RuntimeException {
        public ResourceNotFoundException(String message) {
            super(message);
        }
    }



}
