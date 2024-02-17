package com.example.rent2gojavaproject.repositories;

import com.example.rent2gojavaproject.models.MailConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MailConfigurationRepository extends JpaRepository<MailConfiguration, Integer> {
}
