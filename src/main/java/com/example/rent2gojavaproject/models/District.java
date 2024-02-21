package com.example.rent2gojavaproject.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "districts")
public class District {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "district_name")
    private String districtName;

    @ManyToOne
    @JoinColumn(name = "city_id", nullable = false)
    private City city;


    @Override
    public String toString() {
        return "District{" +
                "id=" + id +
                ", districtName='" + districtName + '\'' +
                ", city=" + (city != null ? city.getCityName() : null) +
                '}';
    }




}

