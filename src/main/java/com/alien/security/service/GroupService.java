package com.alien.security.service;

import jakarta.persistence.EntityNotFoundException;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import org.springframework.transaction.annotation.Transactional;

import com.alien.security.entity.UserModel;
import com.alien.security.entity.Group;
import com.alien.security.repo.GroupRepository;

@Service
public class GroupService {

    @Autowired
    private GroupRepository groupRepository;

    public Group createGroup(Group group, UserModel user){
        group.setUser(user);
        return groupRepository.save(group);
    }

    public List<Group> getGroupByUser(UserModel user) {
        return groupRepository.findByUser(user);
    }

    @Transactional
    public Group updateGroup(Long id, Group updateGroup, UserModel user) {
        Optional<Group> existingGroupOptional = groupRepository.findByIdAndUser(id, user);

        if (existingGroupOptional.isPresent()){
            Group existingGroup = existingGroupOptional.get();

            if (updateGroup.getName() != null && !updateGroup.getName().equals(existingGroup.getName())){
                existingGroup.setName(updateGroup.getName());
            }
            return groupRepository.save(existingGroup);
        }

        throw new EntityNotFoundException("Group with id " + id + "not found");
    }

    public void deleteGroup(Long id, UserModel user){
        Group group = groupRepository.findByIdAndUser(id, user)
                .orElseThrow(() -> new EntityNotFoundException("Group mot found"));
        groupRepository.delete(group);
    }
}
