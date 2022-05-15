package com.study.teamo.service;

import com.study.teamo.domain.Board;
import com.study.teamo.dto.BoardDto;
import com.study.teamo.dto.CreateBoardDto;
import com.study.teamo.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardService {

  @Autowired
  private final BoardRepository boardRepository;

  public BoardDto createBoard(CreateBoardDto request) {
    Board board = new Board(request.getTitle(), request.getContent());
    boardRepository.save(board);
    return BoardDto.from(board);
  }
}
