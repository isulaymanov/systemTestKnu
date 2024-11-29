package com.alien.security.service;

import com.alien.security.entity.Grades;
import com.alien.security.repo.GradesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class GradesService {
    private final GradesRepository gradesRepository;

    @Autowired
    public GradesService(GradesRepository gradesRepository) {
        this.gradesRepository = gradesRepository;
    }

    private int calculateTotalScore(Grades grades) {
        return grades.getPk1() + grades.getOk1() + grades.getSrs1() +
                grades.getPk2() + grades.getOk2() + grades.getSrs2() +
                grades.getExam();
    }

    public Grades saveGrades(Grades grades) {
        grades.setTotalScore(calculateTotalScore(grades));
        return gradesRepository.save(grades);
    }

    public Grades getGradesById(Long id) {
        return gradesRepository.findById(id).orElseThrow(() -> new RuntimeException("Grades not found"));
    }


    public List<Grades> getGradesByStudentId(Long studentId) {
        return gradesRepository.findByStudentId(studentId);
    }


    public Grades updateGrades(Long id, Grades updatedGrades) {
        Grades existingGrades = gradesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Grades not found with id " + id));

        // Обновляем поля
        existingGrades.setSemester(updatedGrades.getSemester());
        existingGrades.setPk1(updatedGrades.getPk1());
        existingGrades.setOk1(updatedGrades.getOk1());
        existingGrades.setSrs1(updatedGrades.getSrs1());
        existingGrades.setPk2(updatedGrades.getPk2());
        existingGrades.setOk2(updatedGrades.getOk2());
        existingGrades.setSrs2(updatedGrades.getSrs2());
        existingGrades.setExam(updatedGrades.getExam());

        // Пересчитываем totalScore
        existingGrades.setTotalScore(calculateTotalScore(existingGrades));

        // Сохраняем обновленную запись
        return gradesRepository.save(existingGrades);
    }

    public void deleteGrades(Long id){
        if(gradesRepository.existsById(id)){
            gradesRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Grades not found with id " + id);
        }

    }

    public class ResourceNotFoundException extends RuntimeException {
        public ResourceNotFoundException(String message) {
            super(message);
        }
    }

}
