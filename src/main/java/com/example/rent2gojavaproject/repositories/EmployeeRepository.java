package com.example.rent2gojavaproject.repositories;

import com.example.rent2gojavaproject.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}
