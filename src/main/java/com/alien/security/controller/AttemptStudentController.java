package com.alien.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.alien.security.entity.AttemptStudent;
import com.alien.security.service.AttemptStudentService;
import java.util.List;

@RestController
@RequestMapping("/api/attempt-student")
public class AttemptStudentController {

    private final AttemptStudentService attemptStudentService;

    @Autowired
    public AttemptStudentController(AttemptStudentService attemptStudentService){
        this.attemptStudentService = attemptStudentService;
    }

    @PostMapping
    public ResponseEntity<AttemptStudent> createAttemptStudent(@RequestBody AttemptStudent attemptStudent){
        return ResponseEntity.ok(attemptStudentService.createAttemptStudent(attemptStudent));
    }

    @GetMapping("/testing-group/{testingGroupId}")
    public List<AttemptStudent> getAttemptStudentByTestingGroup(@PathVariable Long testingGroupId){
        return attemptStudentService.getAttemptStudentByTestingGroup(testingGroupId);
    }

    @GetMapping("/testing-group/all")
    public List<AttemptStudent> getAttemptStudentAll(){
        return attemptStudentService.getAttemptStudentAll();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<AttemptStudent> updateAttemptStudent(@PathVariable Long id, @RequestBody AttemptStudent updateAttemptStudent){
        AttemptStudent attemptStudent = attemptStudentService.updateAttemptStudent(id, updateAttemptStudent);
        return ResponseEntity.ok(attemptStudent);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAttemptStudent(@PathVariable Long id){
        attemptStudentService.deleteAttemptStudent(id);
        return ResponseEntity.noContent().build();
    }


}
