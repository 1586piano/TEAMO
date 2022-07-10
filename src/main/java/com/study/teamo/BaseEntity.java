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
  @Column(updatable = false, nullable = false)
  private LocalDateTime createdDateTIme;

  @LastModifiedDate
  @Column(nullable = false)
  private LocalDateTime lastModifiedTime;

  @LastModifiedBy
  @Column(nullable = false)
  private String lastModifiedBy;

  @CreatedBy
  @Column(updatable = false, nullable = false)
  private String createdBy;
}
