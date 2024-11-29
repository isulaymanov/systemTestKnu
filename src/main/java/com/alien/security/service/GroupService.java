package com.alien.security.service;
import com.alien.security.repo.FacultyRepository;
import com.alien.security.repo.GroupRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alien.security.entity.*;
import java.util.List;
import java.util.stream.Collectors;
import com.alien.security.dto.DirectionDTO;
import com.alien.security.dto.FacultyDTO;
import com.alien.security.dto.SimpleEducationLevelDTO;
import java.util.stream.Collectors;
import com.alien.security.entity.*;
import com.alien.security.dto.*;

@Service
public class GroupService {

    private final GroupRepository groupRepository;
    private final FacultyRepository facultyRepository;

    @Autowired
    public GroupService (GroupRepository groupRepository, FacultyRepository facultyRepository) {
        this.groupRepository = groupRepository;
        this.facultyRepository = facultyRepository;
    }

    public Group createGroup(Group group) {
        return groupRepository.save(group);
    }

    public List<GroupDTO> getAllGroups() {
        return groupRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private GroupDTO convertToDTO(Group group) {
        Faculty faculty = group.getFaculty();

        FacultyDTO facultyDTO = new FacultyDTO(
                faculty.getId(),
                faculty.getName(),
                faculty.getCode(),
                faculty.getDescription(),
                faculty.getFoundationDate(),
                faculty.getContactInfo(),
                faculty.getEducationLevels().stream()
                        .map(level -> new SimpleEducationLevelDTO(level.getId(), level.getName(), faculty.getName()))
                        .collect(Collectors.toList())
        );

        return new GroupDTO(
                group.getId(),
                group.getName(),
                group.getCode(),
                group.getYearBeginStudy(),
                facultyDTO
        );
    }

    public Group updateGroup(Long id, Group updateGroup){
        Group existingGroup = groupRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Group not found"));
        existingGroup.setName(updateGroup.getName());
        existingGroup.setFaculty(updateGroup.getFaculty());
        return groupRepository.save(existingGroup);
    }

    public void deleteGroup(Long id){
        if(groupRepository.existsById(id)){
            groupRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Group not found with id " + id);
        }

    }

    public class ResourceNotFoundException extends RuntimeException {
        public ResourceNotFoundException(String message) {
            super(message);
        }
    }
}
