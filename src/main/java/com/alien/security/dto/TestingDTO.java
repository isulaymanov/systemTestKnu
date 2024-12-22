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
public class TestingDTO {
    private Long id;
    private String name;
    private String description;
    private Integer passDate;
    private Integer limitDate;
}
