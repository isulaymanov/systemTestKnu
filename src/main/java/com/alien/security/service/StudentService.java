package com.alien.security.service;
import com.alien.security.repo.ScheduleRepository;
import com.alien.security.repo.StudentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alien.security.entity.*;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }

    public Student createStudent(Student student){
        return studentRepository.save(student);
    }

    public List<Student> getStudentsByGroup(Long groupId) {
        return studentRepository.findByGroupId(groupId);
    }


    public Student updateStudent(Long id, Student updateStudent){
        Student existingStudent = studentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Direction not found"));
        existingStudent.setName(updateStudent.getName());
        existingStudent.setLastName(updateStudent.getLastName());
        existingStudent.setMiddleName(updateStudent.getMiddleName());
        existingStudent.setDateBirt(updateStudent.getDateBirt());
        existingStudent.setGroup(updateStudent.getGroup());
        return studentRepository.save(existingStudent);
    }



    public void deleteStudent(Long id) {
        if(studentRepository.existsById(id)) {
            studentRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Direction Level not found with id " + id);
        }
    }

    public class ResourceNotFoundException extends RuntimeException {
        public ResourceNotFoundException(String message) {
            super(message);
        }
    }
}
