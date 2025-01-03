package com.alien.security.service;

import com.alien.security.entity.Testing;
import com.alien.security.entity.UserModel;
import com.alien.security.repo.TestingRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TestingService {

    @Autowired
    private TestingRepository testingRepository;

    public Testing createTesting(Testing testing, UserModel user){
        testing.setUser(user);
        return testingRepository.save(testing);
    }

    public List<Testing> getTestingByUser(UserModel user){
        return testingRepository.findByUser(user);
    }


    @Transactional
    public Testing updateTesting(Long id, Testing updateTesting, UserModel user) {

        Optional<Testing> existingTestingOptional = testingRepository.findByIdAndUser(id, user);

        if (existingTestingOptional.isPresent()) {
            Testing existingTesting = existingTestingOptional.get();

            if (updateTesting.getName() != null && !updateTesting.getName().equals(existingTesting.getName())) {
                existingTesting.setName(updateTesting.getName());
            }
            if (updateTesting.getDescription() != null && !updateTesting.getDescription().equals(existingTesting.getDescription())) {
                existingTesting.setDescription(updateTesting.getDescription());
            }
            if (updateTesting.getPassDate() != null && !updateTesting.getPassDate().equals(existingTesting.getPassDate())) {
                existingTesting.setPassDate(updateTesting.getPassDate());
            }
            if (updateTesting.getLimitDate() != null && !updateTesting.getLimitDate().equals(existingTesting.getLimitDate())) {
                existingTesting.setLimitDate(updateTesting.getLimitDate());
            }

            return testingRepository.save(existingTesting);
        }

        throw new EntityNotFoundException("Testing with id " + id + " not found");
    }

    public void deteleTesting(Long id,  UserModel user){
        Testing testing = testingRepository.findByIdAndUser(id, user)
                .orElseThrow(() -> new EntityNotFoundException("Testing not found"));
        testingRepository.delete(testing);

    }

}
