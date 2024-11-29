package com.alien.security.controller;
import com.alien.security.dto.DisciplineDTO;
import com.alien.security.dto.ScheludeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.alien.security.entity.*;
import java.util.List;
import org.springframework.http.HttpStatus;
import com.alien.security.service.*;

@RestController
@RequestMapping("/api/schedule")
public class ScheludeController {

    private final ScheludeService scheludeService;

    @Autowired
    public ScheludeController(ScheludeService scheludeService){
        this.scheludeService = scheludeService;
    }

    @PostMapping
    public ResponseEntity<Schedule> createSchedule(@RequestBody Schedule schedule){
        return ResponseEntity.ok(scheludeService.createSchedule(schedule));
    }

    @GetMapping("/all")
    public ResponseEntity<List<ScheludeDTO>> getAllSchedules() {
        System.out.println("getAllSch() called");
        List<ScheludeDTO> res = scheludeService.getAllSchedules();
        if (res.isEmpty()) {
            System.out.println("No Sch found.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        System.out.println("Sch found: " + res.size());
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Schedule> updateSchelude(@PathVariable Long id, @RequestBody Schedule updateSchelude){
        Schedule schedule = scheludeService.updateSchelude(id, updateSchelude);
        return ResponseEntity.ok(schedule);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSchelude(@PathVariable Long id){
        scheludeService.deleteSchelude(id);
        return ResponseEntity.noContent().build();
    }
}
