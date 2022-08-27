package com.study.teamo.dto.user;

import com.study.teamo.domain.auth.User;
import lombok.Builder;
import lombok.Data;

@Data
public class SimpleUserDto {

  private Long id;
  private String name;

  @Builder
  public SimpleUserDto(Long id, String name) {
    this.id = id;
    this.name = name;
  }

  public static SimpleUserDto from(User user) {
    return SimpleUserDto.builder().id(user.getId()).name(user.getName()).build();
  }
}
