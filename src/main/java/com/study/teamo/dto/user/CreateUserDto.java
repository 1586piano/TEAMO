package com.study.teamo.dto.user;

import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
public class CreateUserDto {

  @NotNull
  private final String name;
  @NotNull
  private final String password;
  @NotNull
  private final String auth;

  @Builder
  public CreateUserDto(String name, String password, String auth) {
    this.name = name;
    this.password = password;
    this.auth = auth;
  }
}
