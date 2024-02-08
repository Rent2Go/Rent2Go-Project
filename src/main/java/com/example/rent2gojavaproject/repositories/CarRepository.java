package com.example.rent2gojavaproject.repositories;

import com.example.rent2gojavaproject.models.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CarRepository extends JpaRepository<Car, Integer> {


    boolean existsByPlate(String plate);

    Optional<Car> findByPlate(String plate);

}
