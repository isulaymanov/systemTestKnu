package com.alien.security.service;

import com.alien.security.entity.Faculty;
import com.alien.security.entity.UserModel;
import com.alien.security.repo.FacultyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FacultyService {

    @Autowired
    private FacultyRepository facultyRepository;

    public Faculty createFaculty(Faculty faculty, UserModel user) {
        faculty.setUser(user);
        return facultyRepository.save(faculty);
    }
    // Получение всех факультетов, созданных пользователем
    public List<Faculty> getFacultiesByUser(UserModel user) {
        return facultyRepository.findByUser(user);
    }
    

    @Transactional
    public Faculty updateFaculty(Long id, Faculty updatedFaculty, UserModel user) {
        // Находим факультет по id и проверяем, что он принадлежит пользователю
        Optional<Faculty> facultyOptional = facultyRepository.findByIdAndUser(id, user);
        if (facultyOptional.isPresent()) {
            Faculty existingFaculty = facultyOptional.get();

            // Обновляем только те поля, которые изменены
            if (updatedFaculty.getName() != null && !updatedFaculty.getName().equals(existingFaculty.getName())) {
                existingFaculty.setName(updatedFaculty.getName());
            }
            if (updatedFaculty.getCode() != null && !updatedFaculty.getCode().equals(existingFaculty.getCode())) {
                existingFaculty.setCode(updatedFaculty.getCode());
            }
            if (updatedFaculty.getDescription() != null && !updatedFaculty.getDescription().equals(existingFaculty.getDescription())) {
                existingFaculty.setDescription(updatedFaculty.getDescription());
            }
            if (updatedFaculty.getFoundationDate() != null && !updatedFaculty.getFoundationDate().equals(existingFaculty.getFoundationDate())) {
                existingFaculty.setFoundationDate(updatedFaculty.getFoundationDate());
            }
            if (updatedFaculty.getContactInfo() != null && !updatedFaculty.getContactInfo().equals(existingFaculty.getContactInfo())) {
                existingFaculty.setContactInfo(updatedFaculty.getContactInfo());
            }

            // Сохраняем изменения в базе данных
            return facultyRepository.save(existingFaculty);
        }
        return null; // Если факультет не найден или не принадлежит пользователю
    }



    // Удаление факультета
    public void deleteFaculty(Long id, UserModel user) {
        Faculty faculty = facultyRepository.findByIdAndUser(id, user)
                .orElseThrow(() -> new IllegalArgumentException("Faculty not found or not owned by user"));
        facultyRepository.delete(faculty);
    }
}
