package com.study.teamo.dto.boardpermission;

import com.study.teamo.dto.board.SimpleBoardDto;
import com.study.teamo.dto.user.SimpleUserDto;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
public class BoardPermissionDto {

  private final SimpleBoardDto board;
  private final List<SimpleUserDto> users;

  @Builder
  public BoardPermissionDto(SimpleBoardDto board, List<SimpleUserDto> users) {
    this.board = board;
    this.users = users;
  }
}
