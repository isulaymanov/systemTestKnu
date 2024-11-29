package com.alien.security.controller;
import com.alien.security.repo.DirectionRepository;
import com.alien.security.service.DirectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.alien.security.entity.*;
import java.util.List;
import org.springframework.http.HttpStatus;
import com.alien.security.service.*;
import com.alien.security.dto.*;

@RestController
@RequestMapping("/api/discipline")
public class DisciplineController {
    public final DisciplineService disciplineService;


    @Autowired
    public DisciplineController(DisciplineService disciplineService){
        this.disciplineService = disciplineService;
    }

    @PostMapping
    public ResponseEntity<Discipline> createDiscipline(@RequestBody Discipline discipline){
        return ResponseEntity.ok(disciplineService.createDiscipline(discipline));
    }

    @GetMapping("/all")
    public ResponseEntity<List<DisciplineDTO>> getAllDisciplines() {
        System.out.println("getAllDirection() called");
        List<DisciplineDTO> res = disciplineService.getAllDisciplines();
        if (res.isEmpty()) {
            System.out.println("No Direction levels found.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        System.out.println("Education levels found: " + res.size());
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Discipline> updateDiscipline(@PathVariable Long id, @RequestBody Discipline updateDiscipline){
        Discipline discipline = disciplineService.updateDiscipline(id, updateDiscipline);
        return ResponseEntity.ok(discipline);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDiscipline(@PathVariable Long id){
        disciplineService.deleteDiscipline(id);
        return ResponseEntity.noContent().build();
    }
}

