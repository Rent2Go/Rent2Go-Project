package com.example.rent2gojavaproject.repositories;

import com.example.rent2gojavaproject.models.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BrandRepository extends JpaRepository<Brand,Integer> {

    boolean existsByNameAndIsActiveTrue(String name);
}
