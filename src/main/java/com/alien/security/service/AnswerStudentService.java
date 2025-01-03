package com.alien.security.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.transaction.annotation.Transactional;
import com.alien.security.entity.AnswerStudent;
import com.alien.security.repo.AnswerStudentRepository;
import com.alien.security.dto.AnswerStudentDTO;
import com.alien.security.dto.*;

@Service
public class AnswerStudentService {
    private final AnswerStudentRepository answerStudentRepository;

    @Autowired
    public AnswerStudentService(AnswerStudentRepository answerStudentRepository){
        this.answerStudentRepository = answerStudentRepository;
    }

    public AnswerStudent createAnswerStudent(AnswerStudent answerStudent){
        return answerStudentRepository.save(answerStudent);
    }

    @Transactional(readOnly = true)
    public List<AnswerStudentDTO> getAnswerStudents(Long attemptStudentId) {
        List<AnswerStudent> answerStudents = answerStudentRepository.findByAttemptStudentId(attemptStudentId);

        return answerStudents.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }


    private AnswerStudentDTO convertToDTO(AnswerStudent answerStudent) {
        AnswerStudentDTO answerStudentDTO = new AnswerStudentDTO();

        answerStudentDTO.setId(answerStudent.getId());

        AttemptStudentDTO attemptStudentDTO = new AttemptStudentDTO();
        attemptStudentDTO.setId(answerStudent.getAttemptStudent().getId());
        attemptStudentDTO.setName(answerStudent.getAttemptStudent().getName());
        attemptStudentDTO.setSurname(answerStudent.getAttemptStudent().getSurname());
        attemptStudentDTO.setDateCompletion(answerStudent.getAttemptStudent().getDateCompletion());
        attemptStudentDTO.setResult(answerStudent.getAttemptStudent().getResult());
        attemptStudentDTO.setStartTime(answerStudent.getAttemptStudent().getStartTime());
        attemptStudentDTO.setEndTime(answerStudent.getAttemptStudent().getEndTime());

        if (answerStudent.getAttemptStudent().getTestingGroup() != null) {
            attemptStudentDTO.setTestingGroup(new TestingGroupDTO(
                    answerStudent.getAttemptStudent().getTestingGroup().getId(),
                    new GroupDTO(
                            answerStudent.getAttemptStudent().getTestingGroup().getGroup().getId(),
                            answerStudent.getAttemptStudent().getTestingGroup().getGroup().getName()
                    ),
                    new TestingDTO(
                            answerStudent.getAttemptStudent().getTestingGroup().getTesting().getId(),
                            answerStudent.getAttemptStudent().getTestingGroup().getTesting().getName(),
                            answerStudent.getAttemptStudent().getTestingGroup().getTesting().getDescription(),
                            answerStudent.getAttemptStudent().getTestingGroup().getTesting().getPassDate(),
                            answerStudent.getAttemptStudent().getTestingGroup().getTesting().getLimitDate()
                    )
            ));
        }

        answerStudentDTO.setAttemptStudent(attemptStudentDTO);

        QuestionDTO questionDTO = new QuestionDTO();
        questionDTO.setId(answerStudent.getQuestion().getId());
        questionDTO.setQuestionText(answerStudent.getQuestion().getQuestionText());
        questionDTO.setTesting(new TestingDTO(
                answerStudent.getQuestion().getTesting().getId(),
                answerStudent.getQuestion().getTesting().getName(),
                answerStudent.getQuestion().getTesting().getDescription(),
                answerStudent.getQuestion().getTesting().getPassDate(),
                answerStudent.getQuestion().getTesting().getLimitDate()
        ));
        answerStudentDTO.setQuestion(questionDTO);

        AnswerOptionDTO answerOptionDTO = new AnswerOptionDTO();
        answerOptionDTO.setId(answerStudent.getAnswerOption().getId());
        answerOptionDTO.setAnswerText(answerStudent.getAnswerOption().getAnswerText());
        answerOptionDTO.setIsCorrect(answerStudent.getAnswerOption().getIsCorrect());
        answerOptionDTO.setQuestion(questionDTO);
        answerStudentDTO.setAnswerOption(answerOptionDTO);

        return answerStudentDTO;
    }

    public AnswerStudent updateAnswerStudent(Long id, AnswerStudent updateAnswerStudent){
        AnswerStudent exisitingAnswerStudent = answerStudentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("AnswerStudent not found"));
        exisitingAnswerStudent.setAttemptStudent(updateAnswerStudent.getAttemptStudent());
        exisitingAnswerStudent.setQuestion(updateAnswerStudent.getQuestion());
        exisitingAnswerStudent.setAnswerOption(updateAnswerStudent.getAnswerOption());
        return answerStudentRepository.save(exisitingAnswerStudent);
    }

    public void deleteAnswerStudent(Long id){
        if(answerStudentRepository.existsById(id)){
            answerStudentRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("AnswerStudent not found with id" + id);
        }
    }

    public class ResourceNotFoundException extends RuntimeException {
        public ResourceNotFoundException(String message) {
            super(message);
        }
    }
}
