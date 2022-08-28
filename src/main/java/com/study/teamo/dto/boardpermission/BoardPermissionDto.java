package com.study.teamo.dto.boardpermission;

import com.study.teamo.domain.board.BoardPermission;
import com.study.teamo.dto.board.SimpleBoardDto;
import com.study.teamo.dto.user.SimpleUserDto;
import lombok.Builder;
import lombok.Getter;

@Getter
public class BoardPermissionDto {

  private final SimpleBoardDto board;
  private final SimpleUserDto users;

  @Builder
  public BoardPermissionDto(SimpleBoardDto board, SimpleUserDto users) {
    this.board = board;
    this.users = users;
  }

  public static BoardPermissionDto from(BoardPermission boardPermission) {
    return BoardPermissionDto.builder().board(SimpleBoardDto.from(boardPermission.getBoard()))
        .users(SimpleUserDto.from(boardPermission.getUser())).build();
  }
}
