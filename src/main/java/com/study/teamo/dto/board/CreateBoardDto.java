package com.study.teamo.dto.board;

import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Getter
public class CreateBoardDto {

  @NotNull(message = "title must not be null")
  private final String title;

  @NotNull(message = "content must not be null")
  private final String content;

  @Builder
  public CreateBoardDto(String title, String content) {
    this.title = title;
    this.content = content;
  }
}
