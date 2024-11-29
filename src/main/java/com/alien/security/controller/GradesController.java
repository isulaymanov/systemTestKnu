package com.alien.security.controller;

import com.alien.security.entity.Grades;
import com.alien.security.service.GradesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/api/grades")
public class GradesController {

    private final GradesService gradesService;

    @Autowired
    public GradesController(GradesService gradesService) {
        this.gradesService = gradesService;
    }

    @PostMapping
    public ResponseEntity<Grades> createGrades(@RequestBody Grades grades) {
        Grades savedGrades = gradesService.saveGrades(grades);
        return ResponseEntity.ok(savedGrades);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Grades> getGrades(@PathVariable Long id) {
        Grades grades = gradesService.getGradesById(id);
        return ResponseEntity.ok(grades);
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<Grades>> getGradesByStudentId(@PathVariable Long studentId) {
        List<Grades> gradesList = gradesService.getGradesByStudentId(studentId);
        return ResponseEntity.ok(gradesList);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Grades> updateGrades(@PathVariable Long id, @RequestBody Grades updatedGrades) {
        Grades savedGrades = gradesService.updateGrades(id, updatedGrades);
        return ResponseEntity.ok(savedGrades);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGrades(@PathVariable Long id) {
        gradesService.deleteGrades(id);
        return ResponseEntity.noContent().build();
    }
}
