package com.example.rent2gojavaproject.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private int id;

    @Column(name = "discount_code", nullable = false, unique = true)
    private String discountCode;

    @Column(name = "percentage", nullable = false)
    private double percentage;

    @OneToMany(mappedBy = "discount")
    @JsonIgnore
    private List<Rental> rentals;

    public Discount(int id) {
        this.id = id;
    }
}
