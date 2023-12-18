package com.example.rent2gojavaproject.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "policies")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Policy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "processing_date", nullable = false)
    private LocalDate processingDate;

    @Column(name = "is_active", nullable = false, columnDefinition = "integer default 1")
    private int isActive;

    @Column(name = "is_deleted", nullable = false, columnDefinition = "integer default 0")
    private int isDeleted;

    @Column(name = "created_at", nullable = false)
    @CreationTimestamp
    private LocalDate createdAt;

    @OneToMany(mappedBy = "policy", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Company> companies;

    @OneToMany(mappedBy = "policy", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Car> cars;

    @OneToMany(mappedBy = "policy", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Employee> employees;
}
