package com.study.teamo.dto.board;

import com.study.teamo.domain.Board;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
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
  private final List<String> permissions;

  @Builder
  public BoardDto(String title, String content, LocalDateTime createdDateTIme,
      LocalDateTime lastModifiedTime, String lastModifiedBy, String createdBy,
      List<String> permissions) {
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
            : board.getPermissions().stream().map(bp -> bp.getUsers()).collect(
                Collectors.toList())).build();
  }
}