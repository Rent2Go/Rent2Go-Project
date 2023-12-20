package com.example.rent2gojavaproject.models;

import jakarta.annotation.PreDestroy;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.sql.Update;

import java.time.LocalDate;


@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass

public abstract class BaseEntity {
    @Column(name = "IS_ACTIVE", columnDefinition ="boolean default true")
    protected boolean isActive;
    @Column(name = "CREATE_AT",updatable = false)
    protected LocalDate createdAt;
    @Column(name = "UPDATE_AT")
    protected LocalDate updatedAt;
    @Column(name = "DELETE_AT")
    protected LocalDate deletedAt ;


    @PrePersist
    public void prePersist() {

        this.isActive =true;
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
