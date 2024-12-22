package com.alien.security.service;

import com.alien.security.repo.QuestionRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alien.security.entity.Question;
import java.util.List;

@Service
public class QuestionService {

    private final QuestionRepository questionRepository;

    @Autowired
    public QuestionService(QuestionRepository questionRepository){
        this.questionRepository = questionRepository;
    }

    public Question createQuestion(Question question){
        return questionRepository.save(question);
    }

    public List<Question> getQuestionByTest(Long testingId){
        return questionRepository.findByTestingId(testingId);
    }

    public Question updateQuestion(Long id, Question updateQuestion){
        Question exisitingQuestion = questionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Question not found"));
        exisitingQuestion.setQuestionText(updateQuestion.getQuestionText());
        return questionRepository.save(exisitingQuestion);
    }

    public void deleteQuestion(Long id){
        if(questionRepository.existsById(id)){
            questionRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Question not found with id " + id);
        }
    }
    public class ResourceNotFoundException extends RuntimeException {
        public ResourceNotFoundException(String message) {
            super(message);
        }
    }

}
