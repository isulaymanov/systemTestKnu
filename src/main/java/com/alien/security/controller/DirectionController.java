package com.alien.security.controller;
import com.alien.security.repo.DirectionRepository;
import com.alien.security.service.DirectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.alien.security.entity.*;
import java.util.List;
import org.springframework.http.HttpStatus;
import com.alien.security.service.*;
import com.alien.security.dto.*;
@RestController
@RequestMapping("/api/direction")
public class DirectionController {
    private final DirectionService directionService;

    @Autowired
    public DirectionController (DirectionService directionService) {
        this.directionService = directionService;
    }

    @PostMapping
    public ResponseEntity<Direction> createDirection(@RequestBody Direction direction) {
        return ResponseEntity.ok(directionService.createDirection(direction));
    }

    @GetMapping("/directions")
    public ResponseEntity<List<DirectionDTO>> getAllDirections() {
        System.out.println("getAllDirection() called");
        List<DirectionDTO> res = directionService.getAllDirections();
        if (res.isEmpty()) {
            System.out.println("No Direction levels found.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        System.out.println("Education levels found: " + res.size());
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Direction> updateDirection(@PathVariable Long id, @RequestBody Direction updateDirection) {
        Direction direction = directionService.updateDirection(id, updateDirection);
        return ResponseEntity.ok(direction);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDirection(@PathVariable Long id) {
        directionService.deleteDirection(id);
        return ResponseEntity.noContent().build();
    }

}
