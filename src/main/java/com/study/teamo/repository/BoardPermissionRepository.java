package com.study.teamo.repository;

import com.study.teamo.domain.BoardPermission;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardPermissionRepository extends JpaRepository<BoardPermission, Long> {

  @Query("select bp from BoardPermission bp where board_id=:boardId")
  public List<BoardPermission> getByBoardId(@Param("boardId") Long boardId);
}
