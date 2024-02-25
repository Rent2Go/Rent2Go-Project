package com.example.rent2gojavaproject.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;
import org.hibernate.annotations.SQLDelete;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "customers")
@Data
@AllArgsConstructor
@NoArgsConstructor
//@SQLDelete(sql = "update customers SET IS_ACTIVE = false WHERE id=?")
//@Where(clause = "IS_ACTIVE=true")
@FilterDef(name = "isActiveFilterCustomer", parameters = @ParamDef(name = "isActive", type = Boolean.class))
@Filter(name = "isActiveFilterCustomer", condition = "IS_ACTIVE = :isActive")
public class Customer extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;


    @Column(name=("driver_card_issue_date") ,nullable = true)
    private LocalDate issueDate;

    @Column(name=("driver_car_expiry_date"),nullable = true)
    private LocalDate expiryDate;
    @Column(name="driver_license_age",nullable = true)
    private Integer driverLicenseAge;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    @JsonIgnore
    private User user;


    @OneToMany(mappedBy = "customer")
    @JsonIgnore
    private List<Rental> rentals;


    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", user=" + user +
                '}';
    }

}
