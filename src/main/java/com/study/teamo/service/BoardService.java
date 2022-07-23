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

  //TODO BOARD 권한은 추가 완료. 여려명은 ?
  @Transactional
  public BoardDto addBoardPermission(Long boardId, List<String> users) {
    Board board = boardRepository.findById(boardId)
        .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 보드입니다."));

    List<User> userList = users.stream().map(u -> userRepository.findUserById(u)
            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다.")))
        .collect(Collectors.toList());

    //BoardPermission에 다 저장해도 되지만... 이걸 서비스에서 처리하는게 맞을까?

    BoardPermission boardPermission = new BoardPermission();
    userList.stream().map( u -> boardPermission.addPermission(board, u));

    //boardPermissionRepository.save(boardPermission);
    return BoardDto.from(board);
  }
}
