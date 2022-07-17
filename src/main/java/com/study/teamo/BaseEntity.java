package com.study.teamo;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {

  @CreatedDate
  @Column(updatable = false, nullable = true)
  private LocalDateTime createdDateTIme;

  @LastModifiedDate
  @Column(nullable = true)
  private LocalDateTime lastModifiedTime;

  @LastModifiedBy
  @Column(nullable = true)
  private String lastModifiedBy;

  @CreatedBy
  @Column(updatable = false, nullable = true)
  private String createdBy;
}
