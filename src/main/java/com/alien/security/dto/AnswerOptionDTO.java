package com.alien.security.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnswerOptionDTO {
    private Long id;
    private String answerText;
    private String isCorrect;
    private QuestionDTO question;
}
