package com.alien.security.controller;

import com.alien.security.entity.EducationLevel;
import com.alien.security.service.EducationLevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/api/education-levels")
public class EducationLevelController {

    private final EducationLevelService educationLevelService;

    @Autowired
    public EducationLevelController(EducationLevelService educationLevelService) {
        this.educationLevelService = educationLevelService;
    }

    @PostMapping
    public ResponseEntity<EducationLevel> createEducationLevel(@RequestBody EducationLevel educationLevel) {
        return ResponseEntity.ok(educationLevelService.createEducationLevel(educationLevel));
    }

    @GetMapping
    public ResponseEntity<List<EducationLevel>> getAllEducationLevels() {
        System.out.println("getAllEducationLevels() called");
        List<EducationLevel> res = educationLevelService.getAllEducationLevels();
        if (res.isEmpty()) {
            System.out.println("No education levels found.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        System.out.println("Education levels found: " + res.size());
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }



    @PatchMapping("/{id}")
    public ResponseEntity<EducationLevel> updateEducationLevel(@PathVariable Long id,
                                                               @RequestBody EducationLevel updatedEducationLevel) {
        EducationLevel educationLevel = educationLevelService.updateEducationLevel(id, updatedEducationLevel);
        return ResponseEntity.ok(educationLevel);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEducationLevel(@PathVariable Long id) {
        educationLevelService.deleteEducationLevel(id);
        return ResponseEntity.noContent().build();
    }

}
