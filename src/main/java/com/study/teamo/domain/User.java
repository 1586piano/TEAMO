package com.study.teamo.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
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
  @Column(name = "SYS_ID")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long sysId;

  @Getter
  @Column(name = "ID", unique = true)
  private String id;

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
  public User(String id, String password, String auth) {
    this.id = id;
    this.password = password;
    this.auth = auth;
  }
}