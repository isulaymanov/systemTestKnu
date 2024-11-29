package com.alien.security.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Data;
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentDTO {
    private Long id;
    private String name;
    private String code;
    private String foundationDate;
    private FacultyDTO faculty;
    public DepartmentDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

}
