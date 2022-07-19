package com.study.teamo.repository;

import com.study.teamo.domain.BoardPermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardPermissionRepository extends JpaRepository<BoardPermission, Long> {

}
