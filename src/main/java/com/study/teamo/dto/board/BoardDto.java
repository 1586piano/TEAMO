package com.study.teamo.dto.board;

import com.study.teamo.domain.Board;
import com.study.teamo.domain.BoardPermission;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
public class BoardDto {

  private final String title;
  private final String content;
  private final LocalDateTime createdDateTIme;
  private final LocalDateTime lastModifiedTime;
  private final String lastModifiedBy;
  private final String createdBy;
  private final List<BoardPermission> permissions;

  @Builder
  public BoardDto(String title, String content, LocalDateTime createdDateTIme,
      LocalDateTime lastModifiedTime, String lastModifiedBy, String createdBy,
      List<BoardPermission> permissions) {
    this.title = title;
    this.content = content;
    this.createdDateTIme = createdDateTIme;
    this.lastModifiedTime = lastModifiedTime;
    this.lastModifiedBy = lastModifiedBy;
    this.createdBy = createdBy;
    this.permissions = permissions == null ? new ArrayList<>() : permissions;
  }

  public static BoardDto from(Board board) {
    return BoardDto.builder().title(board.getTitle()).content(board.getContent())
        .createdDateTIme(board.getCreatedDateTIme()).lastModifiedTime(board.getLastModifiedTime())
        .lastModifiedBy(board.getLastModifiedBy()).createdBy(board.getCreatedBy())
        .permissions(board.getPermissions() == null ? new ArrayList<>()
            : board.getPermissions()).build();
  }
}