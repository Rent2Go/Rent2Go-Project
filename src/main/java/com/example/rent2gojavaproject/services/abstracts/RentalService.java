package com.example.rent2gojavaproject.services.abstracts;

import com.example.rent2gojavaproject.services.dtos.requests.rentalRequest.AddRentalRequest;
import com.example.rent2gojavaproject.services.dtos.requests.rentalRequest.UpdateRentalRequest;
import com.example.rent2gojavaproject.services.dtos.responses.userResponse.GetRentalListResponse;
import com.example.rent2gojavaproject.services.dtos.responses.userResponse.GetRentalResponse;

import java.util.List;

public interface RentalService {
    List<GetRentalListResponse> getAllRentals();
    GetRentalResponse getById(int id);

    String addRental(AddRentalRequest addRentalRequest);

    String updateRental (UpdateRentalRequest updateRentalRequest);

    String deleteRental(int id);
}
