package com.alien.security.entity;

import jakarta.persistence.*;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonBackReference;
@Entity
@Table(name = "education_level")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EducationLevel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "faculty_id", nullable = false)
    //@JsonIgnore Из за этой хрени меняеться метод создание обновлнения, нашел решение в создание dto
    @JsonBackReference // Обратная ссылка помогло убирает связвает
    private Faculty faculty;
}
