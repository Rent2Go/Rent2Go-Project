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
@Table(name = "discounts")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Discount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "discount_code", nullable = false, unique = true)
    private String discountCode;

    @Column(name = "percentage", nullable = false)
    private Double percentage;

    @Column(name = "is_active", nullable = false, columnDefinition = "integer default 1")
    private int isActive;

    @Column(name = "is_deleted", nullable = false, columnDefinition = "integer default 0")
    private int isDeleted;

    @Column(name = "created_at" , nullable = false)
    @CreationTimestamp
    private LocalDate createdAt;

    @OneToMany(mappedBy = "discount")
    @JsonIgnore
    private List<Rental> rentals;

    public Discount(int id, String discountCode,double percentage) {
        this.id = id;
        this.discountCode = discountCode;
        this.percentage = percentage;
    }

}
