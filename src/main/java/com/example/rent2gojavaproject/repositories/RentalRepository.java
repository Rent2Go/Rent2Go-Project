package com.example.rent2gojavaproject.repositories;

import com.example.rent2gojavaproject.models.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface RentalRepository extends JpaRepository<Rental, Integer> {





    @Query(value = "SELECT CheckUniqueDiscount(:customerId, :discountId)", nativeQuery = true)
    String checkUniqueDiscount(@Param("customerId") int customerId, @Param("discountId") int discountId);



    boolean existsByCustomerIdAndDiscountId(int customerId, int discountId);
}
