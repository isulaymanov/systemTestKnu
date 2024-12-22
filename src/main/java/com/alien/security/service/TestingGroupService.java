package com.alien.security.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

import com.alien.security.dto.TestingDTO;
import com.alien.security.dto.GroupDTO;
import com.alien.security.dto.TestingGroupDTO;
import com.alien.security.entity.TestingGroup;
import com.alien.security.repo.TestingGroupRepository;

@Service
public class TestingGroupService {

    private final TestingGroupRepository testingGroupRepository;

    @Autowired
    public TestingGroupService(TestingGroupRepository testingGroupRepository){
        this.testingGroupRepository = testingGroupRepository;
    }


    public TestingGroup createTestingGroup(TestingGroup testingGroup) {
        // Можно добавить дополнительную логику валидации или обработки данных
        return testingGroupRepository.save(testingGroup);
    }

//    public List<TestingGroup> getTestingGroup(Long groupId){
//        return testingGroupRepository.findByGroupId(groupId);
//    }

    public List<TestingGroupDTO> getAllTestingGroupsByGroupId(Long groupId) {
        List<TestingGroup> testingGroups = testingGroupRepository.findByGroupId(groupId);

        return testingGroups.stream().map(testingGroup -> {
            GroupDTO groupDTO = new GroupDTO(testingGroup.getGroup().getId(), testingGroup.getGroup().getName());
            TestingDTO testingDTO = new TestingDTO(testingGroup.getTesting().getId(), testingGroup.getTesting().getName(),
                    testingGroup.getTesting().getDescription(), testingGroup.getTesting().getPassDate(),
                    testingGroup.getTesting().getLimitDate());
            return new TestingGroupDTO(testingGroup.getId(), groupDTO, testingDTO);
        }).collect(Collectors.toList());
    }


    public List<TestingGroupDTO> getTestingGroupAll() {
        List<TestingGroup> testingGroups = testingGroupRepository.findAll();
        return testingGroups.stream().map(testingGroup -> {
            GroupDTO groupDTO = new GroupDTO(testingGroup.getGroup().getId(), testingGroup.getGroup().getName());
            TestingDTO testingDTO = new TestingDTO(testingGroup.getTesting().getId(), testingGroup.getTesting().getName(),
                    testingGroup.getTesting().getDescription(), testingGroup.getTesting().getPassDate(),
                    testingGroup.getTesting().getLimitDate());
            return new TestingGroupDTO(testingGroup.getId(), groupDTO, testingDTO);
        }).collect(Collectors.toList());
    }



    public TestingGroup updateTestingGroup(Long id, TestingGroup updateTestingGroup){
        TestingGroup exisitingTestingGroup = testingGroupRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("TestingGroup not found"));
        exisitingTestingGroup.setGroup(updateTestingGroup.getGroup());
        exisitingTestingGroup.setTesting(updateTestingGroup.getTesting());
        return testingGroupRepository.save(exisitingTestingGroup);
    }

    public void deleteTestingGroup(Long id){
        if(testingGroupRepository.existsById(id)){
            testingGroupRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("TestingGroup not found with id" + id);
        }
    }

    public class ResourceNotFoundException extends RuntimeException {
        public ResourceNotFoundException(String message) {
            super(message);
        }
    }
}
