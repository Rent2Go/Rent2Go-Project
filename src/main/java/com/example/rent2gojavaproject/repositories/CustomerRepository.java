package com.example.rent2gojavaproject.repositories;

import com.example.rent2gojavaproject.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {


    @Query("SELECT c FROM Customer c WHERE c.user.id = :userId")
    Customer getCustomerByUserId(int userId);
}
