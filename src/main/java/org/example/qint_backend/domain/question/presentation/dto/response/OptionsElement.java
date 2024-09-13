package org.example.qint_backend.domain.question.presentation.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class OptionsElement {
   private Long answerId;
   private String text;

   public OptionsElement(Long answerId, String text) {
      this.answerId = answerId;
      this.text = text;
   }
}
