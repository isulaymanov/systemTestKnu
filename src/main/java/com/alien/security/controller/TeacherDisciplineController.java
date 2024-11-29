package com.alien.security.controller;
import com.alien.security.repo.TeacherDisciplineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.alien.security.entity.*;
import java.util.List;
import org.springframework.http.HttpStatus;
import com.alien.security.service.*;
import com.alien.security.dto.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.Authentication;

@RestController
@RequestMapping("/api/teacher-discipline")
public class TeacherDisciplineController {

     private final TeacherDisciplineService teacherDisciplineService;
     private final TeacherDisciplineRepository teacherDisciplineRepository;
    private final Userservice userservice;

    @Autowired
    public TeacherDisciplineController(TeacherDisciplineService teacherDisciplineService, TeacherDisciplineRepository teacherDisciplineRepository, Userservice userservice){
        this.teacherDisciplineService = teacherDisciplineService;
        this.teacherDisciplineRepository = teacherDisciplineRepository;
        this.userservice = userservice;
    }

    @PostMapping("/assign")
    public ResponseEntity<?> assignDisciplineToTeacher(@RequestParam Integer teacherId, @RequestParam Long disciplineId){
        TeacherDiscipline assignment = teacherDisciplineService.assignDisciplineToTeacher(teacherId,disciplineId);
        return ResponseEntity.ok(assignment);
    }

    @GetMapping("/all")
    public ResponseEntity<List<TeacherDisciplineDTO>> getAllTeacherDisciplines() {
        List<TeacherDisciplineDTO> teacherDisciplineDetails = teacherDisciplineRepository.findAllTeacherDisciplineDetails();
        return ResponseEntity.ok(teacherDisciplineDetails);
    }


    @PatchMapping("/{id}")
    public ResponseEntity<TeacherDiscipline> updateTeacherDiscipline(
            @PathVariable Long id,
            @RequestParam Integer teacherId,
            @RequestParam Long disciplineId) {

        TeacherDiscipline updatedTeacherDiscipline = teacherDisciplineService.updateTeacherDiscipline(id, teacherId, disciplineId);
        return ResponseEntity.ok(updatedTeacherDiscipline);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeacherDiscipline(@PathVariable Long id){
        teacherDisciplineService.deleteTeacherDiscipline(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/disciplines")
    public ResponseEntity<List<DisciplineDTO>> getAllDisciplinesForCurrentTeacher() {
        // Получаем текущего аутентифицированного пользователя
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserModel user = (UserModel) authentication.getPrincipal();

        Integer teacherId = (int) user.getId(); // ID из сущности UserModel

        List<DisciplineDTO> disciplines = teacherDisciplineService.getAllDisciplinesByTeacher(teacherId);
        return ResponseEntity.ok(disciplines);
    }

    @GetMapping("/{disciplineId}/groups")
    public ResponseEntity<List<Group>> getGroupsByDiscipline(@PathVariable Long disciplineId) {
        List<Group> groups = teacherDisciplineService.getGroupsByDiscipline(disciplineId);
        return ResponseEntity.ok(groups);
    }



}
