package com.alien.security.service;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alien.security.entity.*;
import com.alien.security.dto.*;
import java.util.List;
import java.util.stream.Collectors;
import com.alien.security.repo.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.Authentication;
import java.util.ArrayList;
import java.util.stream.Collectors;

@Service
public class TeacherDisciplineService {

    private final TeacherDisciplineRepository teacherDisciplineRepository;
    private final UserRepo userRepo;
    private final DisciplineRepository disciplineRepository;
    private final ScheduleRepository scheduleRepository;

    @Autowired
    public TeacherDisciplineService(TeacherDisciplineRepository teacherDisciplineRepository, UserRepo userRepo, DisciplineRepository disciplineRepository,ScheduleRepository scheduleRepository){
        this.teacherDisciplineRepository = teacherDisciplineRepository;
        this.userRepo = userRepo;
        this.disciplineRepository = disciplineRepository;
        this.scheduleRepository = scheduleRepository;
    }

    public TeacherDiscipline assignDisciplineToTeacher(int teacherId, Long disciplineId) {
        UserModel teacher = userRepo.findById(teacherId)
                .orElseThrow(() -> new RuntimeException("Teacher not found"));
        Discipline discipline = disciplineRepository.findById(disciplineId)
                .orElseThrow(() -> new RuntimeException("Discipline not found"));
        TeacherDiscipline assignment = new TeacherDiscipline();
        assignment.setUser(teacher);
        assignment.setDiscipline(discipline);
        return teacherDisciplineRepository.save(assignment);
    }



    public TeacherDiscipline updateTeacherDiscipline(Long id, Integer teacherId, Long disciplineId) {
        TeacherDiscipline existingTeacherDiscipline = teacherDisciplineRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("TeacherDiscipline not found"));
        UserModel user = userRepo.findById(teacherId)
                .orElseThrow(() -> new EntityNotFoundException("Teacher not found"));
        Discipline discipline = disciplineRepository.findById(disciplineId)
                .orElseThrow(() -> new EntityNotFoundException("Discipline not found"));
        existingTeacherDiscipline.setUser(user);
        existingTeacherDiscipline.setDiscipline(discipline);
        return teacherDisciplineRepository.save(existingTeacherDiscipline);
    }



    public void deleteTeacherDiscipline(Long id) {
        if (teacherDisciplineRepository.existsById(id)){
            teacherDisciplineRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Not teacher-discipline this id");
        }
    }


    public class ResourceNotFoundException extends RuntimeException {
        public ResourceNotFoundException(String message) {
            super(message);
        }
    }

    public List<Group> getGroupsByDiscipline(Long disciplineId) {
        // Получаем все расписания для данной дисциплины
        List<Schedule> schedules = scheduleRepository.findByDisciplineId(disciplineId);

        // Извлекаем группы из расписаний и возвращаем уникальные группы
        return schedules.stream()
                .map(Schedule::getGroup) // Извлекаем группы из расписаний
                .distinct() // Убираем дубликаты
                .collect(Collectors.toList());
    }


    public List<DisciplineDTO> getAllDisciplinesByTeacher(Integer teacherId) {
        // Получаем все записи для преподавателя
        List<TeacherDiscipline> teacherDisciplines = teacherDisciplineRepository.findByUserId(teacherId);

        // Преобразуем их в список дисциплин
        List<DisciplineDTO> disciplines = new ArrayList<>();
        for (TeacherDiscipline teacherDiscipline : teacherDisciplines) {
            Discipline discipline = teacherDiscipline.getDiscipline();

            // Преобразуем дисциплину в DTO и добавляем в список
            DisciplineDTO disciplineDTO = new DisciplineDTO(
                    discipline.getId(),
                    discipline.getName(),
                    discipline.getCode(),
                    discipline.getDescription(),
                    new DepartmentDTO(discipline.getDepartment().getId(), discipline.getDepartment().getName()),
                    new DirectionDTO(discipline.getDirection().getId(), discipline.getDirection().getName())
            );
            disciplines.add(disciplineDTO);
        }
        return disciplines;
    }


}
