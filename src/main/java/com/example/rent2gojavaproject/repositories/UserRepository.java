package com.example.rent2gojavaproject.repositories;

import com.example.rent2gojavaproject.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByEmail(String email);
    Optional<User> findByEmailAndName(String email, String name);



    boolean existsByPhoneNumber(String phoneNumber);
    @Transactional
    @Modifying
    @Query("UPDATE User a " +
            "SET a.isEnabled = TRUE WHERE a.email = :email")
    int enableAppUser(String email);

    @Transactional
    @Modifying
    @Query("UPDATE User a " +
            "SET a.password = :password WHERE a.email = :email")
    void passwordChange(String email,String password);

}
