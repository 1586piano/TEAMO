package com.study.teamo.controller;

import com.study.teamo.dto.boardpermission.UpdateBoardPermissionDto;
import com.study.teamo.dto.user.SimpleUserDto;
import com.study.teamo.service.BoardPermissionService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/permission")
public class BoardPermissionController {

  @Autowired
  private final BoardPermissionService boardPermissionService;

  @GetMapping("/{id}")
  public List<SimpleUserDto> getBoardPermission(@PathVariable("id") Long id) {
    return boardPermissionService.getPermissionedUserByBoardID(id);
  }

  @PutMapping("/{id}")
  public List<SimpleUserDto> updateBoardPermission(@PathVariable("id") Long id,
      @RequestBody UpdateBoardPermissionDto request) {
    return boardPermissionService.modifyBoardPermissionToUsers(id, request);
  }
}
