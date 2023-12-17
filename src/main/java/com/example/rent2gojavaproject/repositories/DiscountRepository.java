package com.example.rent2gojavaproject.repositories;

import com.example.rent2gojavaproject.models.Discount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiscountRepository extends JpaRepository<Discount,Integer> {
    Discount findByDiscountCode(String discountCode);

}
