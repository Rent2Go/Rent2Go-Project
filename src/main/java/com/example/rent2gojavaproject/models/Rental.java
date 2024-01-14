package com.example.rent2gojavaproject.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;
import org.hibernate.annotations.SQLDelete;

import java.time.LocalDate;

@Entity
@Table(name = "rentals")
@Data
@AllArgsConstructor
@SQLDelete(sql = "update rentals SET IS_ACTIVE = false WHERE id=?")
//@Where(clause = "IS_ACTIVE=true")
@FilterDef(name = "isActiveFilterRental", parameters = @ParamDef(name = "isActive", type = Boolean.class))
@Filter(name = "isActiveFilterRental", condition = "IS_ACTIVE = :isActive")
public class Rental extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    @Column(name = "return_date", columnDefinition = "DATE DEFAULT NULL")
    private LocalDate returnDate;

    @Column(name = "start_kilometer", nullable = false)
    private int startKilometer;

    @Column(name = "end_kilometer", columnDefinition = "INTEGER DEFAULT NULL")
    private Integer endKilometer;

    @Column(name = "total_price")
    private double totalPrice;

    @ManyToOne
    @JoinColumn(name = "car_id", nullable = false)
    private Car car;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "discount_id", nullable = false)
    private Discount discount;

    public Rental() {
        this.endKilometer = null;
        this.returnDate = null;
        this.discount = new Discount(1, "DEFAULT", 0);
    }

}
