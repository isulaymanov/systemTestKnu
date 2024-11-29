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
@RequestMapping("/api/department")
public class DepartmentController {

    private final DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @PostMapping
    public ResponseEntity<Department> createDepartment(@RequestBody Department department){
        return ResponseEntity.ok(departmentService.createDepartment(department));
    }

    @GetMapping("/all")
    public ResponseEntity<List<DepartmentDTO>> getAllDirections(){
        System.out.println("getAllDepartment() called");
        List<DepartmentDTO> res = departmentService.getAllDirections();
        if (res.isEmpty()){
            System.out.println("No Department found.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        System.out.println("No department found: " + res.size());
        return ResponseEntity.status(HttpStatus.OK).body(res);

    }

    @PatchMapping("/{id}")
    public ResponseEntity<Department> updateDepartment(@PathVariable Long id, @RequestBody Department updateDepartment){
        Department department = departmentService.updateDepatment(id, updateDepartment);
        return ResponseEntity.ok(department);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDepartment(@PathVariable Long id) {
        departmentService.deleteDepartment(id);
        return ResponseEntity.noContent().build();
    }

}