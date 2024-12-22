package com.alien.security.controller;
import org.springframework.http.HttpStatus;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.alien.security.service.TestingGroupService;
import com.alien.security.entity.TestingGroup;
import com.alien.security.dto.TestingGroupDTO;

@RestController
@RequestMapping("/api/testing-group")
public class TestingGroupController {

    private final TestingGroupService testingGroupService;

    @Autowired
    public TestingGroupController(TestingGroupService testingGroupService){
        this.testingGroupService = testingGroupService;
    }

//    @PostMapping
//    public ResponseEntity<TestingGroup> createTestinGroup(@RequestBody TestingGroup testingGroup){
//        return ResponseEntity.ok(testingGroupService.createTestingGroup(testingGroup));
//    }
    @PostMapping
    public ResponseEntity<TestingGroup> createTestingGroup(@RequestBody TestingGroup testingGroup) {
        TestingGroup createdTestingGroup = testingGroupService.createTestingGroup(testingGroup);
        return new ResponseEntity<>(createdTestingGroup, HttpStatus.CREATED);
    }

//    @GetMapping("/testinggroup/{groupId}")
//    public List<TestingGroup> getTestingGroupByGroup(@PathVariable Long groupId){
//        return testingGroupService.getTestingGroup(groupId);
//    }
    @GetMapping("/group/{groupId}")
    public List<TestingGroupDTO> getAllTestingGroupsByGroupId(@PathVariable Long groupId) {
        return testingGroupService.getAllTestingGroupsByGroupId(groupId);
    }

    @GetMapping("/testing-group/all")
    public List<TestingGroupDTO> getAllTestingGroups() {
        return testingGroupService.getTestingGroupAll();
    }


    @PatchMapping("/{id}")
    public ResponseEntity<TestingGroup> updateTestingGroup(@PathVariable Long id, @RequestBody TestingGroup updateTestingGroup){
        TestingGroup testingGroup = testingGroupService.updateTestingGroup(id, updateTestingGroup);

        return ResponseEntity.ok(testingGroup);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTestingGroup(@PathVariable Long id){
        testingGroupService.deleteTestingGroup(id);
        return ResponseEntity.noContent().build();
    }
}
