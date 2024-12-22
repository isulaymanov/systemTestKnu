package com.alien.security.service;

import com.alien.security.repo.AnswerOptionRepository;
import com.alien.security.repo.QuestionRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alien.security.entity.AnswerOption;
import java.util.List;

@Service
public class AnswerOptionService {

    private final AnswerOptionRepository answerOptionRepository;

    @Autowired
    public AnswerOptionService(AnswerOptionRepository answerOptionRepository) {
        this.answerOptionRepository = answerOptionRepository;
    }

    public AnswerOption createAnswerOption(AnswerOption answerOption){
        return answerOptionRepository.save(answerOption);
    }

    public List<AnswerOption> getAnswerOptionByQuestion(Long questionId){
        return answerOptionRepository.findByQuestionId(questionId);
    }

    public AnswerOption updateAnswerOption(Long id, AnswerOption updateAnswerOption){
        AnswerOption exisitingAnswerOption = answerOptionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("AnswerOption not found"));
        exisitingAnswerOption.setAnswerText(updateAnswerOption.getAnswerText());
        exisitingAnswerOption.setIsCorrect(updateAnswerOption.getIsCorrect());
        return answerOptionRepository.save(exisitingAnswerOption);
    }

    public void deleteAnswerOption(Long id){
        if(answerOptionRepository.existsById(id)){
            answerOptionRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("AnswerOption not found with id" + id);
        }
    }

    public class ResourceNotFoundException extends RuntimeException {
        public ResourceNotFoundException(String message) {
            super(message);
        }
    }



}
