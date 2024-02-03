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
@Table(name = "colors")
@SQLDelete(sql = "update colors SET IS_ACTIVE = false WHERE id=?")
//@Where(clause = "IS_ACTIVE=true")
@FilterDef(name = "isActiveFilterColor", parameters = @ParamDef(name = "isActive", type = Boolean.class))
@Filter(name = "isActiveFilterColor", condition = "IS_ACTIVE = :isActive")

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Color extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "hex_code", nullable = false)
    private String hexCode;

    @OneToMany(mappedBy = "color")
    @JsonIgnore
    private List<Car> cars;

}
