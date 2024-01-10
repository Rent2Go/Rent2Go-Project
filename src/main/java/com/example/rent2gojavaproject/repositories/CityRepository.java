package com.example.rent2gojavaproject.repositories;

import com.example.rent2gojavaproject.models.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City,Integer> {
}
