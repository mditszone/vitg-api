package com.vitg.entity;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.Instant;
import java.util.Date;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class AbstractAuditable {

    @Column(name = "createdDate", insertable = true, updatable = false)
    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate = Date.from(Instant.now());

    @Column(name = "updatedDate", insertable = false, updatable = true)
    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDate = Date.from(Instant.now());

    @Column(name = "createdBy", insertable = true, updatable = false)
    @CreatedBy
    protected String createdBy;


    @Column(name = "lastModifiedBy", insertable = false, updatable = true)
    @LastModifiedBy
    protected String lastModifiedBy;

}