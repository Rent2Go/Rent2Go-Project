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

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cities")
@SQLDelete(sql = "update cities SET IS_ACTIVE = false WHERE id=?")
//@Where(clause = "IS_ACTIVE=true")
@FilterDef(name="isActiveFilterCity", parameters=@ParamDef( name="isActive", type=Boolean.class ))
@Filter(name="isActiveFilterCity", condition="IS_ACTIVE = :isActive")
public class City extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "city_name")
    private String cityName;

    @OneToMany(mappedBy = "city", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<District> districts;

    @OneToMany(mappedBy = "city")
    @JsonIgnore
    private List<Employee> employees;

    @OneToMany(mappedBy = "city")
    @JsonIgnore
    private List<Customer> customers;

}
