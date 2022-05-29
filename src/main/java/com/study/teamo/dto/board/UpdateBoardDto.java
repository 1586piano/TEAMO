package com.study.teamo.dto.board;

import lombok.Getter;

@Getter
public class UpdateBoardDto {
  private final String title;
  private final String content;

  public UpdateBoardDto(String title, String content) {
    this.title = title;
    this.content = content;
  }
}
