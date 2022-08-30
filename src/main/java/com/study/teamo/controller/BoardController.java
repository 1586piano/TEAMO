package com.study.teamo.controller;

import com.study.teamo.dto.board.BoardDto;
import com.study.teamo.dto.board.CreateBoardDto;
import com.study.teamo.dto.board.UpdateBoardDto;
import com.study.teamo.service.BoardService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

  @Autowired
  private final BoardService boardService;

  @PostMapping("")
  public BoardDto createBoard(@RequestBody CreateBoardDto request) {
    return boardService.createBoard(request);
  }

  @GetMapping("")
  public List<BoardDto> getBoard() {
    return boardService.getBoards();
  }

  @GetMapping("/{id}")
  public BoardDto getBoards(@PathVariable("id") Long id) {
    return boardService.getBoard(id);
  }

  @DeleteMapping("/{id}")
  public void deleteBoard(@PathVariable("id") Long id) {
    boardService.deleteBoard(id);
  }

  @PutMapping("/{id}")
  public BoardDto updateBoard(@PathVariable("id") Long id, @RequestBody UpdateBoardDto request) {
    return boardService.updateBoard(id, request);
  }
}
