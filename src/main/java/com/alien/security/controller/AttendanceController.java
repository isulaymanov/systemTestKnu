package com.alien.security.controller;
import com.alien.security.entity.Attendance;
import com.alien.security.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/attendance")
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;

    @PostMapping("/create")
    public Attendance createAttendance(
            @RequestParam Long studentId,
            @RequestParam String selectedDate, // Выбранная дата расписания
            @RequestParam boolean status) {
        System.out.println("Received studentId: " + studentId);
        System.out.println("Received selectedDate: " + selectedDate);
        return attendanceService.createAttendance(studentId, selectedDate, status);
    }

    @GetMapping("/available-dates")
    public List<String> getAvailableScheduleDates(@RequestParam Long studentId) {
        return attendanceService.getAvailableScheduleDates(studentId);
    }

    /**
     * Обновить посещаемость студента на указанную дату.
     *
     * @param attendanceId Идентификатор записи посещаемости.
     * @param studentId Идентификатор студента.
     * @param selectedDate Дата посещаемости.
     * @param status Новый статус посещаемости (true/false).
     * @return Обновленную запись посещаемости.
     */
    @PatchMapping("/update/{attendanceId}")
    public Attendance updateAttendance(
            @PathVariable Long attendanceId,
            @RequestParam Long studentId,
            @RequestParam String selectedDate,
            @RequestParam boolean status) {

        return attendanceService.updateAttendance(attendanceId, studentId, selectedDate, status);
    }


    /**
     * Получить все посещаемости для студента по его ID.
     * @param studentId Идентификатор студента.
     * @return Список посещаемостей.
     */
    @GetMapping("/get/{studentId}")
    public List<Attendance> getAllAttendances(
            @PathVariable Long studentId) {

        return attendanceService.getAllAttendancesByStudentId(studentId);
    }

    /**
     * Удалить посещаемость по ID для конкретного студента.
     * @param studentId Идентификатор студента.
     * @param attendanceId Идентификатор посещаемости.
     */
    @DeleteMapping("/delete/{studentId}/{attendanceId}")
    public void deleteAttendance(
            @PathVariable Long studentId,
            @PathVariable Long attendanceId) {

        attendanceService.deleteAttendanceByStudentId(studentId, attendanceId);
    }
}
