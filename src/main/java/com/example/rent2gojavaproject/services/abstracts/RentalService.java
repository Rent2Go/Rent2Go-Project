package com.example.rent2gojavaproject.services.abstracts;

import com.example.rent2gojavaproject.core.utilities.results.DataResult;
import com.example.rent2gojavaproject.core.utilities.results.Result;
import com.example.rent2gojavaproject.services.dtos.requests.rentalRequest.AddRentalRequest;
import com.example.rent2gojavaproject.services.dtos.requests.rentalRequest.UpdateRentalRequest;
import com.example.rent2gojavaproject.services.dtos.responses.customerResponse.GetCustomerListResponse;
import com.example.rent2gojavaproject.services.dtos.responses.rentalResponse.GetRentalListResponse;
import com.example.rent2gojavaproject.services.dtos.responses.rentalResponse.GetRentalResponse;
import com.example.rent2gojavaproject.services.dtos.responses.userResponse.GetUserListResponse;
import com.example.rent2gojavaproject.services.dtos.responses.userResponse.GetUserResponse;

import java.util.List;

public interface RentalService {
    DataResult<List<GetRentalListResponse>> getAllRentals();
    DataResult<GetRentalResponse> getById(int id);

    Result addRental(AddRentalRequest addRentalRequest);

    Result updateRental (UpdateRentalRequest updateRentalRequest);

    Result deleteRental(int id);
    DataResult<Iterable<GetRentalListResponse>> findAll(boolean isDeleted);
}
