package com.example.rent2gojavaproject.repositories;

import com.example.rent2gojavaproject.models.ImageData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ImageDataRepository extends JpaRepository<ImageData,Integer> {

   Optional<ImageData> findByName(String fileName);
}
