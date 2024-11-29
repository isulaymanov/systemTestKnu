package com.alien.security.service;
import com.alien.security.dto.DepartmentDTO;
import com.alien.security.repo.DepartmentRepository;
import com.alien.security.repo.DirectionRepository;
import com.alien.security.repo.FacultyRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alien.security.entity.*;
import java.util.List;
import java.util.stream.Collectors;
import com.alien.security.dto.DirectionDTO;
import com.alien.security.dto.FacultyDTO;
import com.alien.security.dto.SimpleEducationLevelDTO;


@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentService (DepartmentRepository departmentRepository){
        this.departmentRepository = departmentRepository;
    }


    public Department createDepartment(Department department){
        return departmentRepository.save(department);
    }

    public List<DepartmentDTO> getAllDirections() {
        return departmentRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private DepartmentDTO convertToDTO(Department department) {
        Faculty faculty = department.getFaculty();
        FacultyDTO facultyDTO = new FacultyDTO(
                faculty.getId(),
                faculty.getName(),
                faculty.getCode(),
                faculty.getDescription(),
                faculty.getFoundationDate(),
                faculty.getContactInfo(),
                faculty.getEducationLevels().stream()
                        .map(level -> new SimpleEducationLevelDTO(level.getId(), level.getName(), faculty.getName())) // Передаем имя факультета
                        .collect(Collectors.toList())
        );

        return new DepartmentDTO(
                department.getId(),
                department.getName(),
                department.getCode(),
                department.getFoundationDate(),
                facultyDTO
        );
    }

    public Department updateDepatment(Long id, Department updateDepartment){
        Department existingDepartment = departmentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("department not found"));
        existingDepartment.setName(updateDepartment.getName());
        existingDepartment.setCode(updateDepartment.getCode());
        existingDepartment.setFoundationDate(updateDepartment.getFoundationDate());
        existingDepartment.setFaculty(updateDepartment.getFaculty());
        return departmentRepository.save(existingDepartment);

    }

    public void deleteDepartment(Long id) {
        if (departmentRepository.existsById(id)){
            departmentRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Not department this id");
        }
    }

    public class ResourceNotFoundException extends RuntimeException {
        public ResourceNotFoundException(String message) {
            super(message);
        }
    }

}