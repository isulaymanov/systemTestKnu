package com.alien.security.entity;

import jakarta.persistence.*;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "student")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String lastName;
    private String middleName;
    private String dateBirt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_group_id", nullable = false)
    @JsonBackReference
    private Group group;

}
