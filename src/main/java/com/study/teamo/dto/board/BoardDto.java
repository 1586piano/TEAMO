package com.study.teamo.dto.board;

import com.study.teamo.domain.Board;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;

@Data
public class BoardDto {

  private final String title;
  private final String content;
  private final LocalDateTime createdDateTIme;
  private final LocalDateTime lastModifiedTime;
  private final String lastModifiedBy;

  @Builder
  public BoardDto(String title, String content, LocalDateTime createdDateTIme,
      LocalDateTime lastModifiedTime, String lastModifiedBy) {
    this.title = title;
    this.content = content;
    this.createdDateTIme = createdDateTIme;
    this.lastModifiedTime = lastModifiedTime;
    this.lastModifiedBy = lastModifiedBy;
  }

  public static BoardDto from(Board board) {
    return BoardDto.builder().title(board.getTitle()).content(board.getContent())
        .createdDateTIme(board.getCreatedDateTIme()).lastModifiedTime(board.getLastModifiedTime())
        .lastModifiedBy(board.getLastModifiedBy()).build();
  }
}