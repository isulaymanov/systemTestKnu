package com.alien.security.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnswerStudentDTO {
    private Long id;
    private AttemptStudentDTO attemptStudent;
    private QuestionDTO question;
    private AnswerOptionDTO answerOption;
}
