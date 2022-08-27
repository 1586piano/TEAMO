package com.study.teamo.dto.board;

import com.study.teamo.domain.board.Board;
import lombok.Builder;
import lombok.Data;

@Data
public class SimpleBoardDto {

  private final Long id;
  private final String title;

  @Builder
  public SimpleBoardDto(Long id, String title) {
    this.id = id;
    this.title = title;
  }

  public static SimpleBoardDto from(Board board) {
    return SimpleBoardDto.builder().id(board.getId()).title(board.getTitle()).build();
  }
}