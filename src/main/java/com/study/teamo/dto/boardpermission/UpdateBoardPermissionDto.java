package com.study.teamo.dto.boardpermission;

import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
public class UpdateBoardPermissionDto {

  private final List<Long> userPermissions;

  @Builder
  public UpdateBoardPermissionDto(List<Long> userPermissions) {
    this.userPermissions = userPermissions;
  }
}
