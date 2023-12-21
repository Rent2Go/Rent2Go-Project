package com.example.rent2gojavaproject.services.abstracts;

import com.example.rent2gojavaproject.core.utilities.results.DataResult;
import com.example.rent2gojavaproject.core.utilities.results.Result;
import com.example.rent2gojavaproject.models.Color;
import com.example.rent2gojavaproject.models.Discount;
import com.example.rent2gojavaproject.services.dtos.requests.discountRequest.AddDiscountRequest;
import com.example.rent2gojavaproject.services.dtos.requests.discountRequest.UpdateDiscountRequest;
import com.example.rent2gojavaproject.services.dtos.responses.discountResponse.GetDiscountListResponse;
import com.example.rent2gojavaproject.services.dtos.responses.discountResponse.GetDiscountResponse;

import java.util.List;

public interface DiscountService {


    DataResult<List<GetDiscountListResponse>> getAllDiscounts();

    DataResult<GetDiscountResponse> getById(int id);

    Result addDiscount(AddDiscountRequest addDiscountRequest);

    Result updateDiscount(UpdateDiscountRequest updateDiscountRequest);

    Result deleteDiscount(int id);

    Discount findByDiscountCode(String discountCode);
    DataResult<Iterable<GetDiscountListResponse>> findAll(boolean isDeleted);
}
