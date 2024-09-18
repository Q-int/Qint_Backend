package org.example.qint_backend.domain.question.presentation.dto.response;

import lombok.Getter;
import org.example.qint_backend.domain.question.domain.Answer;

@Getter
public class OptionsElement {
   private Long answerId;
   private String text;

   public OptionsElement(Long answerId, String text) {
      this.answerId = answerId;
      this.text = text;
   }

   public static OptionsElement of(Answer answer) {
      return new OptionsElement(answer.getId(), answer.getText());
   }
}
