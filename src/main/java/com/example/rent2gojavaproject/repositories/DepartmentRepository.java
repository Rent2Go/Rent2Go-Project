package com.example.rent2gojavaproject.repositories;

import com.example.rent2gojavaproject.models.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department,Integer> {

}
