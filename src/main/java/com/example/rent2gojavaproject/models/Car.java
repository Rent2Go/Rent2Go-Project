package com.example.rent2gojavaproject.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;
import org.hibernate.annotations.SQLDelete;
import java.util.List;

@Entity
@Table(name = "cars")
@Getter
@SQLDelete(sql = "update cars SET IS_ACTIVE = false WHERE id=?")
//@Where(clause = "IS_ACTIVE=true")
@FilterDef(name="isActiveFilterCar", parameters=@ParamDef( name="isActive", type=Boolean.class ))
@Filter(name="isActiveFilterCar", condition="IS_ACTIVE = :isActive")
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Car extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "kilometer", nullable = false)
    private int kilometer;

    @Column(name = "year", nullable = false, length = 4)
    private int year;

    @Column(name = "daily_price", nullable = false)
    private double dailyPrice;

    @Column(name = "plate", nullable = false, unique = true)
    private String plate;

    @Enumerated(EnumType.STRING)
    @Column(name = "body_type", nullable = false)
    private BodyType bodyType;

    @Enumerated(EnumType.STRING)
    @Column(name = "fuel_type", nullable = false)
    private FuelType fuelType;

    @Enumerated(EnumType.STRING)
    @Column(name = "gear_type", nullable = false)
    private GearType gearType;

    @Column(name = "cylinder_count", nullable = false)
    private String cylinderCount;

    @Column(name = "engine_power", nullable = false)
    private String enginePower;

    @Column(name = "image_url")
    private String imageUrl;

    @ManyToOne
    @JoinColumn(name = "model_id", nullable = false)
    private Model model;

    @ManyToOne
    @JoinColumn(name = "color_id", nullable = false)
    private Color color;


    @OneToMany(mappedBy = "car")
    @JsonIgnore
    private List<Rental> rentals;



}
