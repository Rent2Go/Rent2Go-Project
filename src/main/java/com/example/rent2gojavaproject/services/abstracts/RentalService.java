package com.example.rent2gojavaproject.services.abstracts;

import com.example.rent2gojavaproject.services.dtos.requests.rentalRequest.AddRentalRequest;
import com.example.rent2gojavaproject.services.dtos.requests.rentalRequest.UpdateRentalRequest;
import com.example.rent2gojavaproject.services.dtos.responses.userResponse.GetUserListResponse;
import com.example.rent2gojavaproject.services.dtos.responses.userResponse.GetUserResponse;

import java.util.List;

public interface RentalService {
    List<GetUserListResponse> getAllRentals();
    GetUserResponse getById(int id);

    String addRental(AddRentalRequest addRentalRequest);

    String updateRental (UpdateRentalRequest updateRentalRequest);

    String deleteRental(int id);
}
