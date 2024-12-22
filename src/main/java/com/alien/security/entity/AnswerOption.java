package com.alien.security.entity;
import jakarta.persistence.*;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "answeroption")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AnswerOption {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String answerText;
    private String isCorrect;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id", nullable = false)
    @JsonBackReference
    private Question question;
}
