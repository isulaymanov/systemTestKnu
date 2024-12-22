package com.alien.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.List;

import com.alien.security.entity.UserModel;
import com.alien.security.entity.Testing;
import com.alien.security.service.TestingService;

@RestController
@RequestMapping("/api/testing")
public class TestingController {

    @Autowired
    private TestingService testingService;

    @PostMapping("/create")
    public Testing createTesting(@RequestBody Testing testing, @AuthenticationPrincipal UserModel user){
        return testingService.createTesting(testing, user);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Testing>> getTesting(@AuthenticationPrincipal UserModel currentUser) {
        List<Testing> testings = testingService.getTestingByUser(currentUser);

        if (testings.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(testings);
    }

    @PatchMapping("/edit/{id}")
    public ResponseEntity<String> updateTesting(
            @PathVariable("id") Long id,
            @RequestBody Testing updateTesting,
            @AuthenticationPrincipal UserModel user){
        try {
            Testing updated = testingService.updateTesting(id, updateTesting, user);
            if (updated != null) {
                return ResponseEntity.status(HttpStatus.CREATED).body("Testing Updated Successfully");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Testing not found or not allowed to update");
            }
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }

    }

    @DeleteMapping("/delete/{id}")
    public void deteleTesting(@PathVariable Long id, @AuthenticationPrincipal UserModel user){
        testingService.deteleTesting(id, user);
    }
}
