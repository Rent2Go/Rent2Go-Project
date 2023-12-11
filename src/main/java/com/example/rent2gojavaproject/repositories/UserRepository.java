package com.example.rent2gojavaproject.repositories;

import com.example.rent2gojavaproject.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {

}
