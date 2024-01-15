package com.example.rent2gojavaproject;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Rent2GoJavaProjectApplication {


    public static void main(String[] args) {
        SpringApplication.run(Rent2GoJavaProjectApplication.class, args);

    }

    @Bean
    public ModelMapper getModelMapper() {
        return new ModelMapper();
    }

}