package com.example.rent2gojavaproject.repositories;

import com.example.rent2gojavaproject.models.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepository extends JpaRepository<Brand, Integer> {

    boolean existsByNameAndIsActiveTrue(String name);
}
