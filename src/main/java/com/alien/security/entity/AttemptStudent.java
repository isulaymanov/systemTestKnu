package com.alien.security.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "attemptstudent")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AttemptStudent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String surname;
    private String dateCompletion;
    private String result;
    private String startTime;
    private String endTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "testinggroup_id", nullable = false)
    @JsonBackReference
    private TestingGroup testingGroup;
}
