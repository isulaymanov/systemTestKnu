package com.alien.security.service;

import com.alien.security.repo.AttemptStudentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alien.security.entity.AttemptStudent;
import java.util.List;

@Service
public class AttemptStudentService {

    private final AttemptStudentRepository attemptStudentRepository;

    @Autowired
    public AttemptStudentService(AttemptStudentRepository attemptStudentRepository){
        this.attemptStudentRepository = attemptStudentRepository;
    }

    public AttemptStudent createAttemptStudent(AttemptStudent attemptStudent){
        return attemptStudentRepository.save(attemptStudent);
    }

    public List<AttemptStudent> getAttemptStudentByTestingGroup(Long testingGroupId){
        return attemptStudentRepository.findByTestingGroupId(testingGroupId);
    }

    public List<AttemptStudent> getAttemptStudentAll(){
        return attemptStudentRepository.findAll();
    }

    public AttemptStudent updateAttemptStudent(Long id, AttemptStudent updateAttemptStudent){
        AttemptStudent exisitingAttemptStudent = attemptStudentRepository.findById(id)
                .orElseThrow(()->new EntityNotFoundException("AttemptStudent not found"));
        exisitingAttemptStudent.setName(updateAttemptStudent.getName());
        exisitingAttemptStudent.setSurname(updateAttemptStudent.getSurname());
        exisitingAttemptStudent.setDateCompletion(updateAttemptStudent.getDateCompletion());
        exisitingAttemptStudent.setResult(updateAttemptStudent.getResult());
        exisitingAttemptStudent.setStartTime(updateAttemptStudent.getStartTime());
        exisitingAttemptStudent.setEndTime(updateAttemptStudent.getEndTime());

        return attemptStudentRepository.save(exisitingAttemptStudent);
    }

    public void deleteAttemptStudent(Long id){
        if(attemptStudentRepository.existsById(id)){
            attemptStudentRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Attempt Student not found with id" + id);
        }
    }

    public class ResourceNotFoundException extends RuntimeException {
        public ResourceNotFoundException(String message) {
            super(message);
        }
    }
}
