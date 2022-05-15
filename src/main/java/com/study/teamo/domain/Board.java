package com.study.teamo.domain;

import com.study.teamo.BaseEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "BOARD")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Board extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "TITLE", length = 100, nullable = false)
  private String title;

  @Column(name = "CONTENTS", length = 1000)
  private String content;

  public Board(String title, String content) {
    super();
    this.title = title;
    this.content = content;
  }
}

