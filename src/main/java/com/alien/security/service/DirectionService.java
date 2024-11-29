package com.alien.security.service;
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
public class DirectionService {

    private final DirectionRepository directionRepository;
    private final FacultyRepository facultyRepository;

    @Autowired
    public DirectionService (DirectionRepository directionRepository, FacultyRepository facultyRepository){
        this.directionRepository = directionRepository;
        this.facultyRepository = facultyRepository;
    }

    public Direction createDirection(Direction direction) {
        return directionRepository.save(direction);
    }


    public List<DirectionDTO> getAllDirections() {
        return directionRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private DirectionDTO convertToDTO(Direction direction) {
        Faculty faculty = direction.getFaculty();
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

        return new DirectionDTO(
                direction.getId(),
                direction.getName(),
                direction.getCode(),
                direction.getDescription(),
                facultyDTO
        );
    }

    public Direction updateDirection(Long id, Direction updateDirection){
        Direction existingDirection = directionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Direction not found"));
        existingDirection.setName(updateDirection.getName());
        existingDirection.setCode(updateDirection.getCode());
        existingDirection.setDescription(updateDirection.getDescription());
        existingDirection.setFaculty(updateDirection.getFaculty());
        return directionRepository.save(existingDirection);
    }

    public void deleteDirection(Long id) {
        if(directionRepository.existsById(id)) {
            directionRepository.deleteById(id);
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
