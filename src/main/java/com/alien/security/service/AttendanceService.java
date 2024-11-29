package com.alien.security.service;
import com.alien.security.entity.Attendance;
import com.alien.security.entity.Schedule;
import com.alien.security.entity.Student;
import com.alien.security.repo.AttendanceRepository;
import com.alien.security.repo.ScheduleRepository;
import com.alien.security.repo.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class AttendanceService {
    @Autowired
    private AttendanceRepository attendanceRepository;

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private StudentRepository studentRepository;

    /**
     * Создать запись посещаемости для студента на выбранную дату из расписания.
     * @param studentId ID студента.
     * @param selectedDate Дата из расписания.
     * @param status Статус посещаемости.
     * @return Созданная запись посещаемости.
     */
    public Attendance createAttendance(Long studentId, String selectedDate, boolean status) {
        // Находим студента по ID
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalArgumentException("Студент с ID " + studentId + " не найден."));

        // Получаем группу студента
        Long groupId = student.getGroup().getId();

        // Находим расписание по группе и дате
        Schedule schedule = scheduleRepository.findByGroupIdAndDate(groupId, selectedDate)
                .orElseThrow(() -> new IllegalArgumentException("Расписание на дату " + selectedDate + " не найдено для группы с ID " + groupId));

        // Создаем запись посещаемости
        Attendance attendance = new Attendance();
        attendance.setStudent(student);
        attendance.setDate(schedule.getDate()); // Устанавливаем дату из расписания
        attendance.setStatus(status);

        // Сохраняем и возвращаем запись посещаемости
        return attendanceRepository.save(attendance);
    }


    public List<String> getAvailableScheduleDates(Long studentId) {
        // Найти студента по ID
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalArgumentException("Студент с ID " + studentId + " не найден."));

        // Получить группу студента
        Long groupId = student.getGroup().getId();

        // Найти все даты расписания для группы
        List<Schedule> schedules = scheduleRepository.findByGroupId(groupId);

        // Преобразовать список расписаний в список дат
        return schedules.stream()
                .map(Schedule::getDate)
                .collect(Collectors.toList());
    }



    /**
     * Обновление посещаемости для студента на указанную дату.
     * @param attendanceId Идентификатор посещаемости.
     * @param studentId Идентификатор студента.
     * @param selectedDate Дата, для которой обновляется посещаемость.
     * @param status Новый статус посещаемости (true/false).
     * @return Обновленная запись посещаемости.
     */
    public Attendance updateAttendance(Long attendanceId, Long studentId, String selectedDate, boolean status) {
        // Найти существующую запись посещаемости по ID
        Optional<Attendance> optionalAttendance = attendanceRepository.findById(attendanceId);

        if (optionalAttendance.isPresent()) {
            Attendance attendance = optionalAttendance.get();

            // Проверяем, что студент и дата совпадают с текущими значениями записи
            if (attendance.getStudent().getId().equals(studentId) && attendance.getDate().equals(selectedDate)) {
                // Обновляем статус посещаемости
                attendance.setStatus(status);
                return attendanceRepository.save(attendance); // Сохраняем обновленную запись
            } else {
                throw new IllegalArgumentException("Некорректные данные для обновления посещаемости.");
            }
        } else {
            throw new IllegalArgumentException("Запись посещаемости не найдена.");
        }
    }


    public List<Attendance> getAllAttendancesByStudentId(Long studentId) {
        return attendanceRepository.findByStudentId(studentId);
    }


    /**
     * Удалить посещаемость по ID и ID студента.
     * @param studentId Идентификатор студента.
     * @param attendanceId Идентификатор посещаемости.
     */
    public void deleteAttendanceByStudentId(Long studentId, Long attendanceId) {
        Optional<Attendance> attendance = attendanceRepository.findById(attendanceId);

        // Если посещаемость найдена, и она принадлежит студенту
        if (attendance.isPresent() && attendance.get().getStudent().getId().equals(studentId)) {
            attendanceRepository.deleteById(attendanceId);
        } else {
            throw new IllegalArgumentException("Посещаемость с таким ID не найдена для указанного студента.");
        }
    }
}
