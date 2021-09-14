package com.system.management.entity;



import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.Instant;

@MappedSuperclass
@Data
@EntityListeners(AuditingEntityListener.class)
public class AbstractBaseEntity {
    @CreatedDate
    @Column(name = "created_date",nullable = false)
    @JsonIgnore
    private Instant createdDate=Instant.now();
    @LastModifiedDate
    @Column(name ="last_modified_date",nullable = false)
    @JsonIgnore
    private Instant lastModifiedDate=Instant.now();
}
