package com.example.rent2gojavaproject.repositories;

import com.example.rent2gojavaproject.models.Bill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillRepository extends JpaRepository <Bill, Integer> {
}
