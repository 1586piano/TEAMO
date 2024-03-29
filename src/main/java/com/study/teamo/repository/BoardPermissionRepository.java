package com.study.teamo.repository;

import com.study.teamo.domain.auth.User;
import com.study.teamo.domain.board.BoardPermission;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardPermissionRepository extends JpaRepository<BoardPermission, Long> {

  @Query("select bp from BoardPermission bp where board_id=:boardId")
  public List<BoardPermission> getByBoardId(@Param("boardId") Long boardId);

  @Query("select bp.user.id from BoardPermission bp where board_id=:boardId")
  public List<Long> getUserIdsByBoardId(@Param("boardId") Long boardId);

  @Query("select bp.user from BoardPermission bp where board_id=:boardId")
  public List<User> getUserByBoardId(@Param("boardId") Long boardId);

  @Modifying
  @Query("delete from BoardPermission where board_id=:boardId and user_id=:userId")
  public void deleteByBoardIdAndUserId(@Param("boardId") Long boardId,
      @Param("userId") Long userId);
}
