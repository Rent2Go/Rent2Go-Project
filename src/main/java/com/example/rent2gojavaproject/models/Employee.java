package com.example.rent2gojavaproject.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "employees")
@Getter
@Setter
@AllArgsConstructor

public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "salary", nullable = false)
    private double salary;

    @Column(name = "is_active", nullable = false, columnDefinition = "integer default 1")
    private int isActive;

    @Column(name = "is_deleted", nullable = false, columnDefinition = "integer default 0")
    private int isDeleted;

    @Column(name = "created_at" , nullable = false)
    @CreationTimestamp
    private LocalDate createdAt;

    @ManyToOne(optional = true)
    @JoinColumn(name = "user_id", columnDefinition = "integer default 1",nullable = false)
    private User user;

    @ManyToOne(optional = true)
    @JoinColumn(name = "department_id", columnDefinition = "integer default 1",nullable = false)
    private Department department;

    @ManyToOne(optional = true)
    @JoinColumn(name = "city_id", columnDefinition = "integer default 1",nullable = false)
    private City city;

    @ManyToOne(optional = true)
    @JoinColumn(name = "county_id", columnDefinition = "integer default 1",nullable = false)
    private County county;

    @OneToMany(mappedBy = "employee")
    @JsonIgnore
    private List<Rental> rentals;


   //  Created empty constructor for default value.
    public Employee() {
        this.user = new User(1);
    }
}
