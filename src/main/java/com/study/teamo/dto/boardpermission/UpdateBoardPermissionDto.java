package com.study.teamo.dto.boardpermission;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
public class UpdateBoardPermissionDto {

  private final List<Long> userPermissions;

  @Builder
  @JsonCreator
  public UpdateBoardPermissionDto(@JsonProperty("userPermissions") List<Long> userPermissions) {
    this.userPermissions = userPermissions == null ? new ArrayList<>() : userPermissions;
  }
}
