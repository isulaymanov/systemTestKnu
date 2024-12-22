package com.alien.security.entity;

import jakarta.persistence.*;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "question")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String questionText;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "testing_id", nullable = false)
    @JsonBackReference
    private Testing testing;
}
