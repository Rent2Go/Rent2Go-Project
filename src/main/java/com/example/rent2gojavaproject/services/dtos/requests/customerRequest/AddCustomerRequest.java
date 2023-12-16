package com.example.rent2gojavaproject.services.dtos.requests.customerRequest;

import com.example.rent2gojavaproject.models.User;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddCustomerRequest {

    private String nationalityId;

    private int userId;
}
