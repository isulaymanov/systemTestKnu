package com.alien.security.service;
import com.alien.security.repo.ScheduleRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alien.security.entity.*;
import com.alien.security.dto.*;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ScheludeService {

    private final ScheduleRepository scheduleRepository;

    @Autowired
    public ScheludeService(ScheduleRepository scheduleRepository){
        this.scheduleRepository = scheduleRepository;
    }

    public Schedule createSchedule(Schedule schedule){
        return scheduleRepository.save(schedule);
    }


    public List<ScheludeDTO> getAllSchedules() {
        return scheduleRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private ScheludeDTO convertToDTO(Schedule schedule) {
        DisciplineDTO disciplineDTO = new DisciplineDTO(
                schedule.getDiscipline().getId(),
                schedule.getDiscipline().getName(),
                schedule.getDiscipline().getCode(),
                schedule.getDiscipline().getDescription(),
                new DepartmentDTO(
                        schedule.getDiscipline().getDepartment().getId(),
                        schedule.getDiscipline().getDepartment().getName(),
                        schedule.getDiscipline().getDepartment().getCode(),
                        schedule.getDiscipline().getDepartment().getFoundationDate(),
                        new FacultyDTO(
                                schedule.getDiscipline().getDepartment().getFaculty().getId(),
                                schedule.getDiscipline().getDepartment().getFaculty().getName(),
                                schedule.getDiscipline().getDepartment().getFaculty().getCode(),
                                schedule.getDiscipline().getDepartment().getFaculty().getDescription(),
                                schedule.getDiscipline().getDepartment().getFaculty().getFoundationDate(),
                                schedule.getDiscipline().getDepartment().getFaculty().getContactInfo(),
                                schedule.getDiscipline().getDepartment().getFaculty().getEducationLevels().stream()
                                        .map(level -> new SimpleEducationLevelDTO(
                                                level.getId(),
                                                level.getName(),
                                                schedule.getDiscipline().getDepartment().getFaculty().getName()))
                                        .collect(Collectors.toList())
                        )
                ),
                new DirectionDTO(
                        schedule.getDiscipline().getDirection().getId(),
                        schedule.getDiscipline().getDirection().getName(),
                        schedule.getDiscipline().getDirection().getCode(),
                        schedule.getDiscipline().getDirection().getDescription(),
                        new FacultyDTO(
                                schedule.getDiscipline().getDirection().getFaculty().getId(),
                                schedule.getDiscipline().getDirection().getFaculty().getName(),
                                schedule.getDiscipline().getDirection().getFaculty().getCode(),
                                schedule.getDiscipline().getDirection().getFaculty().getDescription(),
                                schedule.getDiscipline().getDirection().getFaculty().getFoundationDate(),
                                schedule.getDiscipline().getDirection().getFaculty().getContactInfo(),
                                schedule.getDiscipline().getDirection().getFaculty().getEducationLevels().stream()
                                        .map(level -> new SimpleEducationLevelDTO(
                                                level.getId(),
                                                level.getName(),
                                                schedule.getDiscipline().getDirection().getFaculty().getName()))
                                        .collect(Collectors.toList())
                        )
                )
        );

        GroupDTO groupDTO = new GroupDTO(
                schedule.getGroup().getId(),
                schedule.getGroup().getName(),
                schedule.getGroup().getCode(),
                schedule.getGroup().getYearBeginStudy(),
                new FacultyDTO(
                        schedule.getGroup().getFaculty().getId(),
                        schedule.getGroup().getFaculty().getName(),
                        schedule.getGroup().getFaculty().getCode(),
                        schedule.getGroup().getFaculty().getDescription(),
                        schedule.getGroup().getFaculty().getFoundationDate(),
                        schedule.getGroup().getFaculty().getContactInfo(),
                        schedule.getGroup().getFaculty().getEducationLevels().stream()
                                .map(level -> new SimpleEducationLevelDTO(
                                        level.getId(),
                                        level.getName(),
                                        schedule.getGroup().getFaculty().getName()))
                                .collect(Collectors.toList())
                )
        );

        return new ScheludeDTO(
                schedule.getId(),
                schedule.getDate(),
                schedule.getStart(),
                schedule.getEnd(),
                schedule.getAuditorium(),
                schedule.getTypeActivity(),
                schedule.getNotes(),
                disciplineDTO,
                groupDTO
        );
    }

    public Schedule updateSchelude(Long id, Schedule updateShedule){
        Schedule exisitingSchedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Discpline not found"));
        exisitingSchedule.setDate(updateShedule.getDate());
        exisitingSchedule.setStart(updateShedule.getStart());
        exisitingSchedule.setEnd(updateShedule.getEnd());
        exisitingSchedule.setAuditorium(updateShedule.getAuditorium());
        exisitingSchedule.setTypeActivity(updateShedule.getTypeActivity());
        exisitingSchedule.setNotes(updateShedule.getNotes());
        exisitingSchedule.setDiscipline(updateShedule.getDiscipline());
        exisitingSchedule.setGroup(updateShedule.getGroup());
        return scheduleRepository.save(exisitingSchedule);
    }


    public void deleteSchelude(Long id) {
        if (scheduleRepository.existsById(id)){
            scheduleRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Not schelude this id");
        }
    }

    public class ResourceNotFoundException extends RuntimeException {
        public ResourceNotFoundException(String message) {
            super(message);
        }
    }
}
