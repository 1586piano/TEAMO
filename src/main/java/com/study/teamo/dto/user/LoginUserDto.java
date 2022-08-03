package com.study.teamo.dto.user;

import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LoginUserDto {

  @NotNull
  private String name;
  @NotNull
  private String password;
}
