package com.study.teamo.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Table(name = "BOARD_PERMISSION")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardPermission {

  @Id
  @Column(name = "ID")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Setter
  @ManyToOne
  @JoinColumn(name = "BOARD_ID")
  private Board board;

  @Setter
  @ManyToOne
  @JoinColumn(name = "USER_ID")
  private User user;

  @Builder
  public BoardPermission(Board board, User user) {
    this.board = board;
    this.user = user;
  }

  public String getUsers() {
    return this.user.getId();
  }
}
