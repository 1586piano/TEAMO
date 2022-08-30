package com.study.teamo.service;

import com.study.teamo.domain.auth.User;
import com.study.teamo.domain.board.Board;
import com.study.teamo.domain.board.BoardPermission;
import com.study.teamo.dto.boardpermission.UpdateBoardPermissionDto;
import com.study.teamo.dto.user.SimpleUserDto;
import com.study.teamo.repository.BoardPermissionRepository;
import com.study.teamo.repository.BoardRepository;
import com.study.teamo.repository.UserRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
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
  public List<SimpleUserDto> addBoardPermissionToUsers(Long boardId, List<Long> users) {
    Board board = boardRepository.findById(boardId)
        .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 보드입니다."));

    List<SimpleUserDto> userDtoList = new ArrayList<>();
    for (Long userId : users) {
      User user = userRepository.findById(userId)
          .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));
      userDtoList.add(SimpleUserDto.from(user));
      BoardPermission boardPermission = new BoardPermission(board, user);
      board.addPermission(boardPermission);
      boardPermissionRepository.save(boardPermission);
    }
    return userDtoList;
  }

  @Transactional
  public List<SimpleUserDto> modifyBoardPermissionToUsers(Long boardId,
      UpdateBoardPermissionDto request) {
    Board board = boardRepository.findById(boardId)
        .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 보드입니다."));

    if (board.getCreatedBy() != userDetailsServiceImpl.getCurrentUser().getName()) {
      throw new IllegalArgumentException("게시물 권한 수정은 생성자만 가능합니다.");
    }

    List<BoardPermission> boardPermissions = boardPermissionRepository.getByBoardId(boardId);

    //board에 권한을 가진 기존 사용자 ID 추출
    List<Long> alreadyUserIdsWithPermissions = boardPermissions.stream().map(bp -> bp.getUserId())
        .collect(
            Collectors.toList());
    //board에 권한을 가질 새로운 사용자 ID 중복없이 추출
    Set<Long> userIdsToBeGrantedPermissions = request.getUserPermissions().stream()
        .collect(Collectors.toSet());
    //기존에 부여된, 삭제될 권한 추출 (새로 추가될 권한과 중복되는 것을 제외하고)
    alreadyUserIdsWithPermissions.removeAll(userIdsToBeGrantedPermissions);
    //기존에 부여된 권한 삭제
    alreadyUserIdsWithPermissions.stream()
        .forEach(p -> boardPermissionRepository.deleteByBoardIdAndUserId(boardId, p));

    return addBoardPermissionToUsers(boardId,
        (List<Long>) userIdsToBeGrantedPermissions);
  }

  public List<SimpleUserDto> getPermissionedUserByBoardID(Long boardId) {
    return boardPermissionRepository.getUserByBoardId(boardId).stream()
        .map(u -> SimpleUserDto.from(u)).collect(
            Collectors.toList());
  }

  public List<Long> getPermissionedUserIdsByBoardID(Long boardId) {
    return boardPermissionRepository.getUserIdsByBoardId(boardId);
  }
}
