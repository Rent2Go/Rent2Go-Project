package com.example.rent2gojavaproject.services.concretes;

import com.example.rent2gojavaproject.services.abstracts.RentalService;
import com.example.rent2gojavaproject.services.dtos.requests.rentalRequest.AddRentalRequest;
import com.example.rent2gojavaproject.services.dtos.requests.rentalRequest.UpdateRentalRequest;
import com.example.rent2gojavaproject.services.dtos.responses.userResponse.GetRentalListResponse;
import com.example.rent2gojavaproject.services.dtos.responses.userResponse.GetRentalResponse;

import java.util.List;

public class RentalManager implements RentalService {
    @Override
    public List<GetRentalListResponse> getAllRentals() {
        return null;
    }

    @Override
    public GetRentalResponse getById(int id) {
        return null;
    }

    @Override
    public String addRental(AddRentalRequest addRentalRequest) {
        return null;
    }

    @Override
    public String updateRental(UpdateRentalRequest updateRentalRequest) {
        return null;
    }

    @Override
    public String deleteRental(int id) {
        return null;
    }
}
