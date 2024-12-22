package com.alien.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.alien.security.entity.AnswerOption;
import com.alien.security.service.AnswerOptionService;
import java.util.List;

@RestController
@RequestMapping("/api/answer-option")
public class AnswerOptionController {

    private final AnswerOptionService answerOptionService;

    @Autowired
    public AnswerOptionController(AnswerOptionService answerOptionService){
        this.answerOptionService = answerOptionService;
    }

    @PostMapping
    public ResponseEntity<AnswerOption> createAnswerOption(@RequestBody AnswerOption answerOption){
        return ResponseEntity.ok(answerOptionService.createAnswerOption(answerOption));
    }

    @GetMapping("/question/{questionId}")
    public List<AnswerOption> getAnswerOptionByQuestion(@PathVariable Long questionId){
        return answerOptionService.getAnswerOptionByQuestion(questionId);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<AnswerOption> updateAnswerOption(@PathVariable Long id, @RequestBody AnswerOption updateAnsweroption){
        AnswerOption answerOption = answerOptionService.updateAnswerOption(id, updateAnsweroption);
        return ResponseEntity.ok(answerOption);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAnswerOption(@PathVariable Long id){
        answerOptionService.deleteAnswerOption(id);
        return ResponseEntity.noContent().build();
    }
}
