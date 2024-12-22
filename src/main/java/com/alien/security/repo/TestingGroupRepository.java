package com.alien.security.repo;

import com.alien.security.entity.TestingGroup;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

@Repository
public interface TestingGroupRepository extends JpaRepository<TestingGroup, Long>{
    List<TestingGroup> findByGroupId(Long groupId);
}
