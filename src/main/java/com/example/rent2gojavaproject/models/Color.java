package com.example.rent2gojavaproject.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.*;
import java.util.List;

@Entity
@Table(name = "colors")
@SQLDelete(sql = "update colors SET IS_ACTIVE = false WHERE id=?")
//@Where(clause = "IS_ACTIVE=true")
@FilterDef(name="isActiveFilterColor", parameters=@ParamDef( name="isActive", type=Boolean.class ))
@Filter(name="isActiveFilterColor", condition="IS_ACTIVE = :isActive")

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Color extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "color")
    @JsonIgnore
    private List<Car> cars;

}
