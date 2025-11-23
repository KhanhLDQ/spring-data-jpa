package org.tommap.springdatajpacourse.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter @Setter
//enable automatic population of audit properties e.g. created_at | created_by | ... when entity is persisted or updated
@EntityListeners(AuditingEntityListener.class)
/*
    - provide JPA mapping inheritance without creating a separate database table
    - audit properties are included directly in each child entity's table
 */
@MappedSuperclass
public abstract class BaseAuditEntity {
    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @CreatedBy
    @Column(nullable = false, updatable = false)
    private String createdBy;

    @LastModifiedDate
    @Column(insertable = false)
    private LocalDateTime lastModifiedAt;

    @LastModifiedBy
    @Column(insertable = false)
    private String lastModifiedBy;
}
