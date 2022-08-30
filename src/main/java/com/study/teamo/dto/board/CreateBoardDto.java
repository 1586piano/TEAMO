package com.study.teamo.dto.board;

import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Getter
public class CreateBoardDto {

  @NotNull(message = "title must not be null")
  private final String title;

  @NotNull(message = "content must not be null")
  private final String content;

  //TODO Board 생성 시, UserPermission이 같이 입력되는 것이 자연스럽지 않은가?
  private final List<Long> userPermissions;

  @Builder
  public CreateBoardDto(String title, String content,
      List<Long> userPermissions) {
    this.title = title;
    this.content = content;
    this.userPermissions = userPermissions == null ? new ArrayList<>() : userPermissions;
  }
}
