package com.alien.security.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FacultyDTO {
    private Long id;
    private String name;
    private String code;
    private String description;
    private LocalDate foundationDate;  // Изменено на LocalDate
    private String contactInfo;
    private List<SimpleEducationLevelDTO> educationLevels;
}
