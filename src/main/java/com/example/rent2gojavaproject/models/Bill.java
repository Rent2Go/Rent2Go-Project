package com.example.rent2gojavaproject.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;
import org.hibernate.annotations.SQLDelete;

import java.time.LocalDate;

@Entity
@Table(name = "bills")
@Data
@AllArgsConstructor
@NoArgsConstructor
@SQLDelete(sql = "update bills SET IS_ACTIVE = false WHERE id=?")
//@Where(clause = "IS_ACTIVE=true")
@FilterDef(name = "isActiveFilterBill", parameters = @ParamDef(name = "isActive", type = Boolean.class))
@Filter(name = "isActiveFilterBill", condition = "IS_ACTIVE = :isActive")

public class Bill extends BaseEntity {

    @Column(name = "rental_start_date", nullable = false)
    LocalDate rentalStartDate;
    @Column(name = "rental_end_date", nullable = false)
    LocalDate rentalEndDate;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "bill_no", nullable = false, unique = true)
    private String no;
    @Column(name = "total_rental_date", nullable = false)
    private short totalRentalDate;
    @Column(name = "rental_price", nullable = false)
    private double rentalPrice;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;


}
