package com.alien.security.dto;

import lombok.*;
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
public class ScheludeDTO {
    private Long id;
    private String date;
    private String start;
    private String end;
    private String auditorium;
    private String type_activity;
    private String notes;

    private DisciplineDTO discipline;
    private GroupDTO group;
}
