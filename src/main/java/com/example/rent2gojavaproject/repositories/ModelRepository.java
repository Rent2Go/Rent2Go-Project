package com.example.rent2gojavaproject.repositories;

import com.example.rent2gojavaproject.models.Model;
import com.example.rent2gojavaproject.services.dtos.responses.modelResponse.GetModelListResponse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ModelRepository extends JpaRepository<Model,Integer> {


}
