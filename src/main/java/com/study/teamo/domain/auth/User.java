package com.study.teamo.domain.auth;

import com.study.teamo.domain.board.BoardPermission;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

  @Id
  @Getter
  @Column(name = "ID")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Getter
  @Column(name = "NAME", unique = true)
  private String name;

  @Getter
  @Column(name = "PASSWORD")
  private String password;

  @Getter
  @Column(name = "AUTH")
  private String auth;

  @Setter
  @OneToMany(mappedBy = "user")
  private List<BoardPermission> permissions = new ArrayList<>();

  @Builder
  public User(String name, String password, String auth) {
    this.name = name;
    this.password = password;
    this.auth = auth;
  }
}