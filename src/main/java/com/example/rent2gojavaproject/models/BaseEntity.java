package com.example.rent2gojavaproject.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;


@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public abstract class BaseEntity {
    @Column(name = "IS_ACTIVE", columnDefinition = "boolean default false")
    protected boolean isActive;
    @Column(name = "CREATED_AT", updatable = false)
    protected LocalDate createdAt;
    @Column(name = "UPDATED_AT")
    protected LocalDate updatedAt;
    @Column(name = "DELETED_AT")
    protected LocalDate deletedAt;


    @PrePersist
    public void prePersist() {

        this.isActive = true;
        this.createdAt = LocalDate.now();

    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDate.now();

    }

    @PreRemove
    public void preRemove() {
        this.deletedAt = LocalDate.now();
    }


}
