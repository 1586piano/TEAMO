package com.study.teamo.service;

import com.study.teamo.domain.board.Board;
import com.study.teamo.domain.board.BoardPermission;
import com.study.teamo.domain.auth.User;
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
  UserDetailServiceImpl userDetailsServiceImpl;

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

  //TODO BoardPermission 수정 시, 삭제되는 Permission 제거 후 새로운 Permission 추가하도록 수정
  @Transactional
  public void modifyBoardPermissionToUsers(Long boardId, List<Long> users) {
    Board board = boardRepository.findById(boardId)
        .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 보드입니다."));

    if (board.getCreatedBy() != userDetailsServiceImpl.getCurrentUser().getName()) {
      throw new IllegalArgumentException("게시물 권한 수정 권한이 없는 사용자입니다.");
    }

    for (Long userId : users) {
      User user = userRepository.findById(userId)
          .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));
    }

    List<BoardPermission> boardPermissions = boardPermissionRepository.getByBoardId(boardId);

    System.out.println("board : " + boardPermissions.get(0).getBoard().getTitle());
    for (BoardPermission boardPermission : boardPermissions) {
      System.out.println(boardPermission.getUser());
    }
  }

  @Transactional
  public List<Long> getPermissionedUserIdsByBoardID(Long boardId) {
    return boardPermissionRepository.getUserIdsByBoardId(boardId);
  }
  //TODO : BoardPermission 삭제 추가
}
