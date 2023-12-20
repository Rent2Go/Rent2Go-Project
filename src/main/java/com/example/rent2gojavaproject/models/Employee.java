package com.example.rent2gojavaproject.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "employees")
@Getter
@Setter
@AllArgsConstructor

public class Employee extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "salary", nullable = false)
    private double salary;

    @ManyToOne(optional = true)
    @JoinColumn(name = "user_id", columnDefinition = "integer default 1",nullable = false)
    private User user;

    @OneToMany(mappedBy = "employee")
    @JsonIgnore
    private List<Rental> rentals;


   //  Created empty constructor for default value.
    public Employee() {
        this.user = new User(1);
    }
}
