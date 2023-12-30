package com.example.rent2gojavaproject.repositories;

import com.example.rent2gojavaproject.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {

    Optional<User> findByEmail(String email);

}
