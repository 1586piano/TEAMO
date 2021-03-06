package com.study.teamo.dto.board;

import java.util.List;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Getter
public class UpdateBoardDto {

  @NotNull(message = "title must not be null")
  private final String title;
  @NotNull(message = "content must not be null")
  private final String content;

  private final List<Long> userPermissions;

  @Builder
  public UpdateBoardDto(String title, String content,
      List<Long> userPermissions) {
    this.title = title;
    this.content = content;
    this.userPermissions = userPermissions;
  }
}
