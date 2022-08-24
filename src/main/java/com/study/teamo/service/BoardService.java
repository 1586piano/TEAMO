package com.study.teamo.service;

import com.study.teamo.domain.board.Board;
import com.study.teamo.dto.board.BoardDto;
import com.study.teamo.dto.board.CreateBoardDto;
import com.study.teamo.dto.board.UpdateBoardDto;
import com.study.teamo.repository.BoardRepository;
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
  private final BoardPermissionService boardPermissionService;

  @Autowired
  UserDetailServiceImpl userDetailsServiceImpl;

  @Transactional
  public BoardDto createBoard(CreateBoardDto request) {
    Board board = new Board(request.getTitle(), request.getContent());
    boardRepository.save(board);
    return boardPermissionService.addBoardPermissionToUsers(board.getId(),
        request.getUserPermissions());
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

    List<Long> authorizedUsers = boardPermissionService.getPermissionedUserIdsByBoardID(
        board.getId());

    //TODO 테스트 작성
    if (!authorizedUsers.contains(userDetailsServiceImpl.getCurrentUser().getId())) {
      throw new IllegalArgumentException("게시물 수정 권한이 없는 사용자입니다.");
    }

    boardPermissionService.modifyBoardPermissionToUsers(board.getId(),
        request.getUserPermissions());
    board.setTitle(request.getTitle());
    board.setContent(request.getContent());

    return BoardDto.from(board);
  }
}
