package com.example.rent2gojavaproject.services.abstracts;

import com.example.rent2gojavaproject.core.utilities.results.DataResult;
import com.example.rent2gojavaproject.core.utilities.results.Result;
import com.example.rent2gojavaproject.models.Rental;
import com.example.rent2gojavaproject.services.dtos.requests.rentalRequest.AddRentalRequest;
import com.example.rent2gojavaproject.services.dtos.requests.rentalRequest.UpdateRentalRequest;
import com.example.rent2gojavaproject.services.dtos.responses.rentalResponse.GetRentalListResponse;
import com.example.rent2gojavaproject.services.dtos.responses.rentalResponse.GetRentalResponse;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RentalService {
    DataResult<List<GetRentalListResponse>> getAllRentals();

    DataResult<GetRentalResponse> getById(int id);

    Result addRental(AddRentalRequest addRentalRequest);

    Result updateRental(UpdateRentalRequest updateRentalRequest);

    Result deleteRental(int id);

    DataResult<Iterable<GetRentalListResponse>> findAll(boolean isActive);

    boolean findByCustomerIdAndDiscountId(int customerId, int discountId);


   // String checkUniqueDiscount( int customerId, int discountId);

}
