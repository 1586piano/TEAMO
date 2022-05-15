package com.study.teamo.controller;

import com.study.teamo.dto.BoardDto;
import com.study.teamo.dto.CreateBoardDto;
import com.study.teamo.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

  @Autowired
  private final BoardService boardService;

  @PostMapping("/")
  public BoardDto createBoard(CreateBoardDto request) {
    return boardService.createBoard(request);
  }
}
