package com.example.rent2gojavaproject.repositories;

import com.example.rent2gojavaproject.models.Brand;
import com.example.rent2gojavaproject.models.Color;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ColorRepository extends JpaRepository<Color,Integer> {

    @Query("FROM Color b WHERE b.isActive = true")
    List<Color> findAllInactiveColors();


    boolean existsByName(String name);

}
