package com.system.management.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
public class ErrorResponse  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  long id;
    private int status;
    private String message;
    private long timestamp;
    @CreatedDate
    @Column(name = "created_date",nullable = false)
    private Instant lastModifiedDate=Instant.now();
    @LastModifiedDate
    @Column(name ="last_modified_date",nullable = false)
    private Instant createdDate=Instant.now();
}
