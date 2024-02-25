package com.example.rent2gojavaproject.services.abstracts;

import com.example.rent2gojavaproject.core.utilities.results.DataResult;
import com.example.rent2gojavaproject.core.utilities.results.Result;
import com.example.rent2gojavaproject.services.dtos.requests.rentalRequest.AddRentalRequest;
import com.example.rent2gojavaproject.services.dtos.requests.rentalRequest.ReturnRentalRequest;
import com.example.rent2gojavaproject.services.dtos.requests.rentalRequest.UpdateRentalRequest;
import com.example.rent2gojavaproject.services.dtos.responses.rentalResponse.GetRentalListResponse;
import com.example.rent2gojavaproject.services.dtos.responses.rentalResponse.GetRentalResponse;

import java.util.List;

public interface RentalService {
    DataResult<List<GetRentalListResponse>> getAllRentals();

    DataResult<List<GetRentalListResponse>> getAllRentals(int pageNo, int pageSize);

    DataResult<GetRentalResponse> getById(int id);

    DataResult<Integer> addRental(AddRentalRequest addRentalRequest);

    Result updateRental(UpdateRentalRequest updateRentalRequest);

    Result vehicleDelivery(ReturnRentalRequest returnRentalRequest);

    Result deleteRental(int id);




    DataResult<List<GetRentalListResponse>> findByReturnDateIsNullAndCarIsActiveTrue();

    DataResult<List<GetRentalListResponse>> findByReturnDateIsNotNullAndCarIsActiveFalse();

    DataResult<Iterable<GetRentalListResponse>> findAll(boolean isActive);

    boolean findByCustomerIdAndDiscountId(int customerId, int discountId);

    DataResult<List<GetRentalListResponse>> findByEmployeeId(int userId);
    // String checkUniqueDiscount( int customerId, int discountId);

}
