package com.example.rent2gojavaproject.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name="settings")
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class PageSettings {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "url", nullable = false)
    private String url;

    @Column(name = "site_logo")
    private String logo;

    @Column(name = "tab_logo")
    private String tabLogo;

    @Column(name = "phoneNumber", nullable = false)
    private String phoneNumber;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "linkedin", nullable = false)
    private String linkedin;

    @Column(name = "instagram", nullable = false)
    private String instagram;

    @Column(name = "github", nullable = false)
    private String github;
}
