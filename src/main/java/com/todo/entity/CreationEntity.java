package com.todo.entity;

import com.todo.constants.CommonConstants;
import com.todo.context.ExecutionContext;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.util.Objects;

@MappedSuperclass
@Data
public class CreationEntity {

    @Column(nullable = false, updatable = false)
    private String createdBy = CommonConstants.EMPTY_STRING;

    @Column
    private String updatedBy;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private Timestamp createdAt;

    @Column
    @UpdateTimestamp
    private Timestamp updatedAt;

    @PrePersist
    public void prePersist() {
        this.setCreatedBy((Objects.isNull(ExecutionContext.get()) ||
                Objects.isNull(ExecutionContext.get().getUsercontext().id())) ?
                "SYSTEM" : ExecutionContext.get().getUsercontext().id().toString());
    }

    @PreUpdate
    public void preUpdate() {
        this.setUpdatedBy((Objects.isNull(ExecutionContext.get()) ||
                Objects.isNull(ExecutionContext.get().getUsercontext().id())) ?
                "SYSTEM" : ExecutionContext.get().getUsercontext().id().toString());
    }
}
