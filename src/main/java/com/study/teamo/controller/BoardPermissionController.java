package com.study.teamo.controller;

import com.study.teamo.dto.board.BoardDto;
import com.study.teamo.service.BoardService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/permission/")
public class BoardPermissionController {

  @Autowired
  private final BoardService boardService;

  @PutMapping("/permission/{boardId}")
  public BoardDto addBoardPermission(@PathVariable("boardId") Long boardId,
      @RequestParam(value = "userId", required = true) List<String> users) {
    return boardService.addBoardPermissionToUsers(boardId, users);
  }

  @PutMapping("/permission/{boardId}")
  public BoardDto modifyBoardPermission(@PathVariable("boardId") Long boardId,
      @RequestParam(value = "userId", required = true) List<String> users) {
    return boardService.addBoardPermissionToUsers(boardId, users);
  }
}
