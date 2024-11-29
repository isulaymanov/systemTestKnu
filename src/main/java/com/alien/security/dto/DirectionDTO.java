package com.alien.security.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DirectionDTO {
    private Long id;
    private String name;
    private String code;
    private String description;
    private FacultyDTO faculty;

    public DirectionDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
