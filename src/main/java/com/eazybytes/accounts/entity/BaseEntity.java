package com.eazybytes.accounts.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;


@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
@ToString
@Setter
@Getter
public class BaseEntity {


    @CreatedDate // asigna la fecha del sistema cuando se crea el dato en la BD
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @CreatedBy // asigna el dato proporcionado en AuditAwareImpl, cuando es creado el dato
    @Column(updatable = false)
    private String createdBy;


    @LastModifiedDate // asigna la fecha del sistema en el cual fue modificado algún dato
    @Column(insertable = false)
    private LocalDateTime updatedAt;

    @LastModifiedBy // asigna el dato proporcionado en AuditAwareImpl, cuando es modificado algún dato
    @Column(insertable = false)
    private String updatedBy;
}
