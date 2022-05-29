package com.study.teamo.dto.user;

import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
public class CreateUserDto {

  @NotNull
  private final String id;
  @NotNull
  private final String password;
  @NotNull
  private final String auth;

  @Builder
  public CreateUserDto(String id, String password, String auth) {
    this.id = id;
    this.password = password;
    this.auth = auth;
  }
}
