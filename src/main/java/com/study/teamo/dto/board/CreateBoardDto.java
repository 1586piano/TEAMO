package com.study.teamo.dto.board;

import com.study.teamo.domain.User;
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

  private final List<User> permissions;

  @Builder
  public CreateBoardDto(String title, String content,
      List<User> permissions) {
    this.title = title;
    this.content = content;
    this.permissions = permissions;
  }
}
