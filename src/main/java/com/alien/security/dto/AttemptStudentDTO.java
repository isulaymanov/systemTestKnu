package com.alien.security.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AttemptStudentDTO {
    private Long id;
    private String name;
    private String surname;
    private String dateCompletion;
    private int result;
    private String startTime;
    private String endTime;
    private TestingGroupDTO testingGroup;
}
