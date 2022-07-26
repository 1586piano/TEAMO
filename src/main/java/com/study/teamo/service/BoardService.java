package com.study.teamo.service;

import com.study.teamo.domain.Board;
import com.study.teamo.domain.BoardPermission;
import com.study.teamo.domain.User;
import com.study.teamo.dto.board.BoardDto;
import com.study.teamo.dto.board.CreateBoardDto;
import com.study.teamo.dto.board.UpdateBoardDto;
import com.study.teamo.repository.BoardPermissionRepository;
import com.study.teamo.repository.BoardRepository;
import com.study.teamo.repository.UserRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BoardService {

  @Autowired
  private final BoardRepository boardRepository;

  @Autowired
  private final UserRepository userRepository;

  @Autowired
  private final BoardPermissionRepository boardPermissionRepository;

  @Transactional
  public BoardDto createBoard(CreateBoardDto request) {
    Board board = new Board(request.getTitle(), request.getContent(), request.getPermissions());
    boardRepository.save(board);
    return BoardDto.from(board);
  }

  @Transactional(readOnly = true)
  public List<BoardDto> getBoards() {
    return boardRepository.findAll().stream().map(b -> BoardDto.from(b))
        .collect(Collectors.toList());
  }

  @Transactional(readOnly = true)
  public BoardDto getBoard(Long id) {
    Board board = boardRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시물입니다."));
    return BoardDto.from(board);
  }

  @Transactional
  public void deleteBoard(Long id) {
    Board board = boardRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시물입니다."));
    boardRepository.delete(board);
  }

  @Transactional
  public BoardDto updateBoard(Long id, UpdateBoardDto request) {
    Board board = boardRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시물입니다."));
    board.setTitle(request.getTitle());
    board.setContent(request.getContent());
    return BoardDto.from(board);
  }

  //TODO : 어색하다. 너무 많은 것을 처리하고 있는 것 같다. 다시 고민해보자.
  @Transactional
  public BoardDto addBoardPermissionToUsers(Long boardId, List<String> users) {
    Board board = boardRepository.findById(boardId)
        .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 보드입니다."));

    BoardPermission boardPermission = new BoardPermission();

    for (String userId : users) {
      User user = userRepository.findUserById(userId)
          .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));
      boardPermission.addPermission(board, user);
      boardPermissionRepository.save(boardPermission);
    }

    return BoardDto.from(board);
  }
  
  //TODO : BoardPermission 삭제 추가
}
