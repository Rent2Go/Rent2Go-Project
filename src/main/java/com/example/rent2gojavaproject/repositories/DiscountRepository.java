package com.example.rent2gojavaproject.repositories;

import com.example.rent2gojavaproject.models.Discount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DiscountRepository extends JpaRepository<Discount, Integer> {
   Optional<Discount> findByDiscountCode(String discountCode);


}
