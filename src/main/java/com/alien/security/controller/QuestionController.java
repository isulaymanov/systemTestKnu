package com.alien.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.alien.security.entity.Question;
import com.alien.security.service.QuestionService;
import java.util.List;

@RestController
@RequestMapping("/api/question")
public class QuestionController {

    private final QuestionService questionService;

    @Autowired
    public QuestionController(QuestionService questionService){
        this.questionService = questionService;
    }

    @PostMapping
    public ResponseEntity<Question> createQuestion(@RequestBody Question question){
        return ResponseEntity.ok(questionService.createQuestion(question));
    }


    @GetMapping("/testing/{testingId}")
    public List<Question> getQuestionByTesting(@PathVariable Long testingId){
        return questionService.getQuestionByTest(testingId);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Question> updateQuestion(@PathVariable Long id, @RequestBody Question updateQuestion) {
        Question question = questionService.updateQuestion(id, updateQuestion);
        return ResponseEntity.ok(question);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuestion(@PathVariable Long id){
        questionService.deleteQuestion(id);
        return ResponseEntity.noContent().build();
    }


}
