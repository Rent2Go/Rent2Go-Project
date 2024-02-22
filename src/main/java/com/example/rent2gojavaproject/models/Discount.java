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

import java.util.List;

@Entity
@Table(name = "discounts")
@Data
@AllArgsConstructor
@NoArgsConstructor
@SQLDelete(sql = "update discounts SET IS_ACTIVE = false WHERE id=?")
//@Where(clause = "IS_ACTIVE=true")
@FilterDef(name = "isActiveFilterDiscount", parameters = @ParamDef(name = "isActive", type = Boolean.class))
@Filter(name = "isActiveFilterDiscount", condition = "IS_ACTIVE = :isActive")
public class Discount extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "discount_code", nullable = false, unique = true)
    private String discountCode;

    @Column(name = "percentage", nullable = false)
    private Double percentage;

    @OneToMany(mappedBy = "discount")
    @JsonIgnore
    private List<Rental> rentals;

    public Discount(int id, String discountCode, double percentage) {
        this.id = id;
        this.discountCode = discountCode;
        this.percentage = percentage;
    }

    @Override
    public String toString() {
        return "Discount{" +
                "id=" + id +
                ", discountCode='" + discountCode + '\'' +
                ", percentage=" + percentage +
                '}';
    }

}
