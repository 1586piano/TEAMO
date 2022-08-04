package com.study.teamo.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Table(name = "BOARD_PERMISSION")
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

  public BoardPermission() {
  }

  //TODO : Board 반환 시에 권한을 가진 유저도 넘겨주기 위함인데, 꼭 이렇게 해야만 하나? 메소드 명은 적절한가? 퍼미션 반환을 위한 DTO를 따로 두는 방법은 어떠한가?
  public String getUsers() {
    return this.user.getName();
  }

  public void addPermission(Board board, User user) {
    this.board = board;
    this.user = user;
  }
}
