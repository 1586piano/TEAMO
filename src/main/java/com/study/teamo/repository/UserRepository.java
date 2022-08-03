package com.study.teamo.repository;

import com.study.teamo.domain.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  @Query("select u from User u where name=:name")
  public Optional<User> findUserByName(@Param("name") String name);
}
