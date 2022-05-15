package com.study.teamo.dto;

import lombok.Getter;

@Getter
public class CreateBoardDto {
  private final String title;
  private final String content;

  public CreateBoardDto(String title, String content) {
    this.title = title;
    this.content = content;
  }
}
