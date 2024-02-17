package com.example.rent2gojavaproject.controllers;

import com.example.rent2gojavaproject.core.utilities.results.DataResult;
import com.example.rent2gojavaproject.core.utilities.results.Result;
import com.example.rent2gojavaproject.models.Discount;
import com.example.rent2gojavaproject.services.abstracts.DiscountService;
import com.example.rent2gojavaproject.services.dtos.requests.discountRequest.AddDiscountRequest;
import com.example.rent2gojavaproject.services.dtos.requests.discountRequest.UpdateDiscountRequest;
import com.example.rent2gojavaproject.services.dtos.responses.discountResponse.GetDiscountListResponse;
import com.example.rent2gojavaproject.services.dtos.responses.discountResponse.GetDiscountResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/discounts")
@CrossOrigin
public class DiscountsController {
    private final DiscountService discountService;

    @GetMapping()
    public DataResult<List<GetDiscountListResponse>> getAllDiscounts() {
        return discountService.getAllDiscounts();
    }

    @GetMapping("/getallisactive")
    public DataResult<Iterable<GetDiscountListResponse>> findAll(@RequestParam boolean isActive) {
        return this.discountService.findAll(isActive);
    }

    @GetMapping("/{id}")
    public DataResult<GetDiscountResponse> getDiscountById(@PathVariable int id) {
        return discountService.getById(id);
    }

    @PostMapping()
    public Result createDiscount(@RequestBody @Valid AddDiscountRequest addDiscountRequest) {
        return discountService.addDiscount(addDiscountRequest);
    }

    @PutMapping()
    public Result updateDiscount(@RequestBody @Valid UpdateDiscountRequest updateDiscountRequest) {
        return discountService.updateDiscount(updateDiscountRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public Result deleteDiscount(@PathVariable int id) {
        return discountService.deleteDiscount(id);
    }

   /* @GetMapping("/code/{discountCode}")
    public Discount findByDiscountCode(@PathVariable String discountCode) {

        return this.discountService.findByDiscountCode( discountCode);
    }*/

    @GetMapping("/code/{discountCode}")
    public DataResult<GetDiscountResponse> findByDiscount(@PathVariable String discountCode) {

        return  this.discountService.findByDiscount( discountCode);
    }
}
