package com.example.rent2gojavaproject.services.concretes;

import com.example.rent2gojavaproject.core.utilities.mappers.ModelMapperService;
import com.example.rent2gojavaproject.core.utilities.results.DataResult;
import com.example.rent2gojavaproject.core.utilities.results.Result;
import com.example.rent2gojavaproject.core.utilities.results.SuccessDataResult;
import com.example.rent2gojavaproject.core.utilities.results.SuccessResult;
import com.example.rent2gojavaproject.models.Discount;
import com.example.rent2gojavaproject.repositories.DiscountRepository;
import com.example.rent2gojavaproject.services.abstracts.DiscountService;
import com.example.rent2gojavaproject.services.dtos.requests.discountRequest.AddDiscountRequest;
import com.example.rent2gojavaproject.services.dtos.requests.discountRequest.UpdateDiscountRequest;
import com.example.rent2gojavaproject.services.dtos.responses.discountResponse.GetDiscountListResponse;
import com.example.rent2gojavaproject.services.dtos.responses.discountResponse.GetDiscountResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DiscountManager implements DiscountService {

    private DiscountRepository discountRepository;
    private ModelMapperService mapperService;
    private String successMessage ="Transaction Successfully !";

    @Override
    public DataResult<List<GetDiscountListResponse>> getAllDiscounts(){
        List<Discount> discounts = this.discountRepository.findAll();
        List<GetDiscountListResponse> responses = discounts.stream().map(discount -> this.mapperService.forResponse().map(discount, GetDiscountListResponse.class)).collect(Collectors.toList());

        return new SuccessDataResult<List<GetDiscountListResponse>>(responses, successMessage);
    }

    @Override
    public DataResult<GetDiscountResponse> getById(int id){
        Discount discount = this.discountRepository.findById(id).orElseThrow(()-> new RuntimeException("Couldn't find discount id"));
        GetDiscountResponse response = this.mapperService.forResponse().map(discount, GetDiscountResponse.class);

        return new SuccessDataResult<GetDiscountResponse>(response, successMessage);
    }

    @Override
    public Result addDiscount(AddDiscountRequest addDiscountRequest){
        Discount discount = this.mapperService.forRequest().map(addDiscountRequest, Discount.class);

        this.discountRepository.save(discount);
        return new SuccessResult("Added Discount successfully");
    }

    @Override
    public Result updateDiscount(UpdateDiscountRequest updateDiscountRequest){
        Discount discount = this.discountRepository.findById(updateDiscountRequest.getId()).orElseThrow(( )-> new RuntimeException("Couldn't find discount id"));

        discount = this.mapperService.forRequest().map(updateDiscountRequest, Discount.class);
        this.discountRepository.save(discount);

        return new SuccessResult("Updated Discount successfully");
    }

    @Override
    public Result deleteDiscount(int id){
        this.discountRepository.findById(id).orElseThrow(() -> new RuntimeException("Couldn't find discount id"));

        return new SuccessResult("Deleted Discount successfully");

    }
}
