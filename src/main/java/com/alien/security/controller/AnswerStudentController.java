package com.alien.security.controller;

import java.util.List;
import com.alien.security.entity.AnswerStudent;
import com.alien.security.service.AnswerStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.alien.security.dto.AnswerStudentDTO;

@RestController
@RequestMapping("/api/answer-student")
public class AnswerStudentController {

    private final AnswerStudentService answerStudentService;

    @Autowired
    public AnswerStudentController(AnswerStudentService answerStudentService){
        this.answerStudentService = answerStudentService;
    }

    @PostMapping
    public ResponseEntity<AnswerStudent> createAnswerStudent(@RequestBody AnswerStudent answerStudent) {
        AnswerStudent createdAnswerStudent = answerStudentService.createAnswerStudent(answerStudent);
        return new ResponseEntity<>(createdAnswerStudent, HttpStatus.CREATED);
    }


    @GetMapping("/attempt/{attemptStudentId}")
    public List<AnswerStudentDTO> getAnswerStudents(@PathVariable Long attemptStudentId) {
        return answerStudentService.getAnswerStudents(attemptStudentId);
    }

//    @GetMapping("/answer-students/{id}")
//    public AnswerStudentDTO getAnswerStudent(@PathVariable Long id) {
//        return answerStudentService.getAnswerStudentDTO(id);
//    }


    @PatchMapping("/{id}")
    public ResponseEntity<AnswerStudent> updateAnswerStudent(@PathVariable Long id, @RequestBody AnswerStudent updateAnswerStudent){
        AnswerStudent answerStudent = answerStudentService.updateAnswerStudent(id, updateAnswerStudent);
        return ResponseEntity.ok(answerStudent);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAnswerStudent(@PathVariable Long id){
        answerStudentService.deleteAnswerStudent(id);
        return ResponseEntity.noContent().build();
    }
}
