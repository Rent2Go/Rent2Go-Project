package com.example.rent2gojavaproject.controllers;

import com.example.rent2gojavaproject.core.utilities.results.DataResult;
import com.example.rent2gojavaproject.core.utilities.results.Result;
import com.example.rent2gojavaproject.services.abstracts.DiscountService;
import com.example.rent2gojavaproject.services.dtos.requests.discountRequest.AddDiscountRequest;
import com.example.rent2gojavaproject.services.dtos.requests.discountRequest.UpdateDiscountRequest;
import com.example.rent2gojavaproject.services.dtos.responses.discountResponse.GetDiscountListResponse;
import com.example.rent2gojavaproject.services.dtos.responses.discountResponse.GetDiscountResponse;
import com.example.rent2gojavaproject.services.dtos.responses.employeeResponse.GetEmployeeListResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.xml.crypto.Data;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/V1/discounts")
public class DiscountsController {
    private final DiscountService discountService;

    @GetMapping("/getAll")
    public DataResult<List<GetDiscountListResponse>> getAllDiscounts(){
        return discountService.getAllDiscounts();
    }

    @GetMapping("/{id}")
    public DataResult<GetDiscountResponse> getDiscountById(@PathVariable int id){
        return discountService.getById(id);
    }

    @PostMapping("/add")
    public Result createDiscount(@RequestBody @Valid AddDiscountRequest addDiscountRequest){
        return discountService.addDiscount(addDiscountRequest);
    }

    @PutMapping("/update")
    public Result updateDiscount(@RequestBody @Valid UpdateDiscountRequest updateDiscountRequest){
        return discountService.updateDiscount(updateDiscountRequest);
    }
    @DeleteMapping("/delete/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public Result deleteDiscount(@PathVariable int id) {
        return discountService.deleteDiscount(id);
    }
}
