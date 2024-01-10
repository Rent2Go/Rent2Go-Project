package com.example.rent2gojavaproject.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ser.Serializers;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;
import org.hibernate.annotations.SQLDelete;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "districts")
@SQLDelete(sql = "update districts SET IS_ACTIVE = false WHERE id=?")
//@Where(clause = "IS_ACTIVE=true")
@FilterDef(name="isActiveFilterDistrict", parameters=@ParamDef( name="isActive", type=Boolean.class ))
@Filter(name="isActiveFilterDistrict", condition="IS_ACTIVE = :isActive")
public class District extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "disctrict_name")
    private String districtName;

    @ManyToOne
    @JoinColumn(name = "city_id", nullable = false)
    private City city;

    @OneToMany(mappedBy = "district")
    @JsonIgnore
    private List<Customer> customers;

    @OneToMany(mappedBy = "district")
    @JsonIgnore
    private List<Employee> employees;
}
