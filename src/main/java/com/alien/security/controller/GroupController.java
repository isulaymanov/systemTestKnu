package com.alien.security.controller;
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
@RequestMapping("/api/group")
public class GroupController {

    private final GroupService groupService;

    @Autowired
    public GroupController (GroupService groupService) {
        this.groupService = groupService;
    }

    @PostMapping
    public ResponseEntity<Group> createGroup(@RequestBody Group group) {
        return ResponseEntity.ok(groupService.createGroup(group));

    }


    @GetMapping("/allGroups")
    public ResponseEntity<List<GroupDTO>> getAllGroups() {
        System.out.println("getAllGroups() called");
        List<GroupDTO> res = groupService.getAllGroups();
        if (res.isEmpty()) {
            System.out.println("No groups found.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        System.out.println("Groups found: " + res.size());
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Group> updateGroup(@PathVariable Long id, @RequestBody Group updateGroup) {
        Group group = groupService.updateGroup(id, updateGroup);
        return ResponseEntity.ok(group);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGroup(@PathVariable Long id) {
        groupService.deleteGroup(id);
        return ResponseEntity.noContent().build();
    }

}
