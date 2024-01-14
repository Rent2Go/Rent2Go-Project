package com.example.rent2gojavaproject.repositories;

import com.example.rent2gojavaproject.models.Model;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModelRepository extends JpaRepository<Model, Integer> {

    boolean existsByName(String name);

}
