package com.study.teamo.dto.board;

import com.study.teamo.domain.BoardPermission;
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

  private final List<BoardPermission> permissions;

  @Builder
  public UpdateBoardDto(String title, String content,
      List<BoardPermission> permissions) {
    this.title = title;
    this.content = content;
    this.permissions = permissions;
  }
}
