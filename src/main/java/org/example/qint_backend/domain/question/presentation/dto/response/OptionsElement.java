package org.example.qint_backend.domain.question.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.qint_backend.domain.question.domain.Answer;

@Getter
@AllArgsConstructor
public class OptionsElement {
   private Long answer_id;
   private String text;

   public static OptionsElement of(Answer answer) {
      return new OptionsElement(answer.getId(), answer.getText());
   }
}
