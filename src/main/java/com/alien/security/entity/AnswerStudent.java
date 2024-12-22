package com.alien.security.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
@Table(name = "answerstudent")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AnswerStudent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JoinColumn(name = "attemptstudent", nullable = false)
    @JsonBackReference(value = "attempt-student-attempt")
    private AttemptStudent attemptStudent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JoinColumn(name = "question_id", nullable = false)
    @JsonBackReference(value = "question-testing-question")
    private Question question;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JoinColumn(name = "answeroption_id", nullable = false)
    @JsonBackReference(value = "answeroption-testing-answeroption")
    private AnswerOption answerOption;
}
