package com.study.teamo.domain;

import com.study.teamo.BaseEntity;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Getter
@Table(name = "BOARD")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class Board extends BaseEntity {

  @Id
  @Column(name = "BOARD_ID")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Setter
  @Column(name = "TITLE", length = 100, nullable = false)
  private String title;

  @Setter
  @Column(name = "CONTENTS", length = 1000)
  private String content;

  @Setter
  @OneToMany(mappedBy = "board")
  private List<BoardPermission> permissions = new ArrayList<>();

  public Board(String title, String content) {
    super();
    this.title = title;
    this.content = content;
  }
}

