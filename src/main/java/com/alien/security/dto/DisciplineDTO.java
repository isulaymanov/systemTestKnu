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
public class DisciplineDTO {
    private Long id;
    private String name;
    private String code;
    private String description;
    private DepartmentDTO department;
    private DirectionDTO direction;
}
