package com.example.rent2gojavaproject.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "our_team")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OurTeam extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "surname", nullable = false)
    private String surname;
    @Column(name = "position", nullable = false)
    private String position;
    @Column(name = "linkedin")
    private String linkedin;
    @Column(name = "github")
    private String github;
    @Column(name = "imageUrl", nullable = false)
    private String imageUrl;
    @Column(name = "description")
    private String description;


}
