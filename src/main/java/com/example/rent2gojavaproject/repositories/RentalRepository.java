package com.example.rent2gojavaproject.repositories;

import com.example.rent2gojavaproject.models.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RentalRepository extends JpaRepository<Rental, Integer> {


    List<Rental> findByCustomerId(int userId);


    @Query(value = "SELECT CheckUniqueDiscount(:customerId, :discountId)", nativeQuery = true)
    String checkUniqueDiscount(@Param("customerId") int customerId, @Param("discountId") int discountId);



    @Query("SELECT r FROM Rental r WHERE r.returnDate IS NULL AND r.car.isActive = false")
    List<Rental> findByReturnDateIsNullAndCarIsActiveTrue();
    @Query("SELECT r FROM Rental r WHERE r.returnDate IS NOT NULL AND r.car.isActive = true")
    List<Rental> findByReturnDateIsNotNullAndCarIsActiveFalse();



    boolean existsByCustomerIdAndDiscountId(int customerId, int discountId);
}
