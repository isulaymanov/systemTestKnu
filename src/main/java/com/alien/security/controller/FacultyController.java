package com.alien.security.controller;

import com.alien.security.entity.Faculty;
import com.alien.security.entity.UserModel;
import com.alien.security.service.FacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
@RestController
@RequestMapping("/api/faculty")
public class FacultyController {

    @Autowired
    private FacultyService facultyService;

    @PostMapping("/create")
    public Faculty createFaculty(@RequestBody Faculty faculty, @AuthenticationPrincipal UserModel user) {
        return facultyService.createFaculty(faculty, user);
    }

    // Получение всех факультетов, созданных пользователем
    // Получение всех факультетов пользователя
    @GetMapping("/all")
    public ResponseEntity<List<Faculty>> getFacultiesForCurrentUser(@AuthenticationPrincipal UserModel currentUser) {
        List<Faculty> faculties = facultyService.getFacultiesByUser(currentUser);

        if (faculties.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(faculties);
    }
    // Частичное обновление факультета
    // Редактирование факультета (PATCH)
    @PatchMapping("/edit/{id}")
    public ResponseEntity<String> updateFaculty(
            @PathVariable("id") Long id,
            @RequestBody Faculty updatedFaculty,
            @AuthenticationPrincipal UserModel user) {
        try {
            Faculty updated = facultyService.updateFaculty(id, updatedFaculty, user);
            if (updated != null) {
                return ResponseEntity.status(HttpStatus.CREATED).body("Faculty Updated Successfully");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Faculty not found or not allowed to update");
            }
        } catch (Exception e) {
            // Логирование ошибки
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }



    // Удаление факультета
    @DeleteMapping("/delete/{id}")
    public void deleteFaculty(@PathVariable Long id, @AuthenticationPrincipal UserModel user) {
        facultyService.deleteFaculty(id, user);
    }
}
