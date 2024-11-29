package com.alien.security.service;
import com.alien.security.dto.*;
import com.alien.security.entity.Department;
import com.alien.security.entity.Faculty;
import com.alien.security.repo.DisciplineRepository;
import com.alien.security.repo.FacultyRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alien.security.entity.Discipline;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Collectors;

@Service
public class DisciplineService {

    private final DisciplineRepository disciplineRepository;


    @Autowired
    public DisciplineService(DisciplineRepository disciplineRepository){
        this.disciplineRepository = disciplineRepository;
    }

    public Discipline createDiscipline(Discipline discipline){
        return disciplineRepository.save(discipline);
    }


    public List<DisciplineDTO> getAllDisciplines() {
        return disciplineRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }


    private DisciplineDTO convertToDTO(Discipline discipline) {
        FacultyDTO departmentFacultyDTO = new FacultyDTO(
                discipline.getDepartment().getFaculty().getId(),
                discipline.getDepartment().getFaculty().getName(),
                discipline.getDepartment().getFaculty().getCode(),
                discipline.getDepartment().getFaculty().getDescription(),
                discipline.getDepartment().getFaculty().getFoundationDate(),
                discipline.getDepartment().getFaculty().getContactInfo(),
                discipline.getDepartment().getFaculty().getEducationLevels().stream()
                        .map(level -> new SimpleEducationLevelDTO(
                                level.getId(),
                                level.getName(),
                                discipline.getDepartment().getFaculty().getName()  // добавляем название факультета
                        ))
                        .collect(Collectors.toList())
        );

        FacultyDTO directionFacultyDTO = new FacultyDTO(
                discipline.getDirection().getFaculty().getId(),
                discipline.getDirection().getFaculty().getName(),
                discipline.getDirection().getFaculty().getCode(),
                discipline.getDirection().getFaculty().getDescription(),
                discipline.getDirection().getFaculty().getFoundationDate(),
                discipline.getDirection().getFaculty().getContactInfo(),
                discipline.getDirection().getFaculty().getEducationLevels().stream()
                        .map(level -> new SimpleEducationLevelDTO(
                                level.getId(),
                                level.getName(),
                                discipline.getDepartment().getFaculty().getName()  // добавляем название факультета
                        ))
                        .collect(Collectors.toList())
        );

        DepartmentDTO departmentDTO = new DepartmentDTO(
                discipline.getDepartment().getId(),
                discipline.getDepartment().getName(),
                discipline.getDepartment().getCode(),
                discipline.getDepartment().getFoundationDate(),
                departmentFacultyDTO
        );

        DirectionDTO directionDTO = new DirectionDTO(
                discipline.getDirection().getId(),
                discipline.getDirection().getName(),
                discipline.getDirection().getCode(),
                discipline.getDirection().getDescription(),
                directionFacultyDTO
        );

        return new DisciplineDTO(
                discipline.getId(),
                discipline.getName(),
                discipline.getCode(),
                discipline.getDescription(),
                departmentDTO,
                directionDTO
        );
    }


    public Discipline updateDiscipline(Long id, Discipline updateDiscipline){
        Discipline exisitingDiscipline = disciplineRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Discpline not found"));
        exisitingDiscipline.setName(updateDiscipline.getName());
        exisitingDiscipline.setCode(updateDiscipline.getCode());
        exisitingDiscipline.setDescription(updateDiscipline.getDescription());
        exisitingDiscipline.setDepartment(updateDiscipline.getDepartment());
        exisitingDiscipline.setDirection(updateDiscipline.getDirection());
        return disciplineRepository.save(exisitingDiscipline);
    }


    public void deleteDiscipline(Long id) {
        if (disciplineRepository.existsById(id)){
            disciplineRepository.deleteById(id);
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
