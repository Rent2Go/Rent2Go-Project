package com.example.rent2gojavaproject.controllers;

import com.example.rent2gojavaproject.core.utilities.results.DataResult;
import com.example.rent2gojavaproject.core.utilities.results.Result;
import com.example.rent2gojavaproject.services.abstracts.CustomerService;
import com.example.rent2gojavaproject.services.dtos.requests.customerRequest.AddCustomerRequest;
import com.example.rent2gojavaproject.services.dtos.requests.customerRequest.UpdateCustomerRequest;
import com.example.rent2gojavaproject.services.dtos.responses.customerResponse.GetCustomerListResponse;
import com.example.rent2gojavaproject.services.dtos.responses.customerResponse.GetCustomerResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/V1/customers")
@AllArgsConstructor
public class CustomersController {
    private final CustomerService customerService;
    @GetMapping("/getall")
    public DataResult<List<GetCustomerListResponse>> getAllCustomer() {

        return this.customerService.getAllCustomer();
    }

    @GetMapping("/{id}")
    public DataResult<GetCustomerResponse> getById(@PathVariable int id) {

        return this.customerService.getById(id);
    }
    @PostMapping("/add")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Result addCustomer(@RequestBody @Valid AddCustomerRequest addCustomerRequest) {
        return this.customerService.addCustomer(addCustomerRequest);
    }

    @PutMapping("/update")
    @ResponseStatus(code = HttpStatus.OK)
    public Result updateCustomer(@RequestBody @Valid UpdateCustomerRequest updateCustomerRequest) {

        return this.customerService.updateCustomer(updateCustomerRequest);
    }
    @DeleteMapping("/delete/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public Result DeleteCustomer(@PathVariable int id) {


        return this.customerService.DeleteCustomer(id);

    }

}
