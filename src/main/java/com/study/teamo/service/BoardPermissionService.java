package com.study.teamo.service;

import com.study.teamo.domain.Board;
import com.study.teamo.domain.BoardPermission;
import com.study.teamo.domain.User;
import com.study.teamo.dto.board.BoardDto;
import com.study.teamo.repository.BoardPermissionRepository;
import com.study.teamo.repository.BoardRepository;
import com.study.teamo.repository.UserRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BoardPermissionService {

  @Autowired
  private final BoardRepository boardRepository;

  @Autowired
  private final UserRepository userRepository;

  @Autowired
  private final BoardPermissionRepository boardPermissionRepository;

  @Transactional
  public BoardDto addBoardPermissionToUsers(Long boardId, List<Long> users) {
    Board board = boardRepository.findById(boardId)
        .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 보드입니다."));

    BoardPermission boardPermission = new BoardPermission();

    for (Long userId : users) {
      User user = userRepository.findById(userId)
          .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));
      boardPermission.addPermission(board, user);
      boardPermissionRepository.save(boardPermission);
    }
    return BoardDto.from(board);
  }

  @Transactional
  public BoardDto modifyBoardPermissionToUsers(Long boardId, List<Long> users) {

    Board board = boardRepository.findById(boardId)
        .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 보드입니다."));

    for (Long userId : users) {
      User user = userRepository.findById(userId)
          .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));
    }
    List<BoardPermission> boardPermissions = boardPermissionRepository.getByBoardId(boardId);

    System.out.println("board : " + boardPermissions.get(0).getBoard().getTitle());
    for (BoardPermission boardPermission : boardPermissions) {
      System.out.println(boardPermission.getUser());
    }
    return BoardDto.from(board);
  }

  @Transactional
  public List<Long> getPermissionedUserIdsByBoardID(Long boardId) {
    return boardPermissionRepository.getUserIdsByBoardId(boardId);
  }
  //TODO : BoardPermission 삭제 추가
}
