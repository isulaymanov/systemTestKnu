package com.alien.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.List;

import com.alien.security.entity.UserModel;
import com.alien.security.entity.Group;
import com.alien.security.service.GroupService;

@RestController
@RequestMapping("/api/group")
public class GroupController {

    @Autowired
    private GroupService groupService;

    @PostMapping("/create")
    public Group createGroup(@RequestBody Group group, @AuthenticationPrincipal UserModel user){
        return groupService.createGroup(group,user);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Group>> getGroup(@AuthenticationPrincipal UserModel currentUser){
        List<Group> groups = groupService.getGroupByUser(currentUser);
        if (groups.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(groups);
    }

    @PatchMapping("/edit/{id}")
    public ResponseEntity<String> updateGroup(
            @PathVariable("id") Long id,
            @RequestBody Group updateGroup,
            @AuthenticationPrincipal UserModel user){
        try {
            Group updated = groupService.updateGroup(id, updateGroup, user);
            if (updated != null){
                return ResponseEntity.status(HttpStatus.CREATED).body("Group Updated Successfully");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Group not found or not allowed to update");
            }
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public void deleteGroup(@PathVariable Long id, @AuthenticationPrincipal UserModel user) {
        groupService.deleteGroup(id, user);
    }
}
