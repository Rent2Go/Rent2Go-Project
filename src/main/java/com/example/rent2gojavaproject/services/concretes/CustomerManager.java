package com.example.rent2gojavaproject.services.concretes;

import com.example.rent2gojavaproject.core.utilities.mappers.ModelMapperService;
import com.example.rent2gojavaproject.core.utilities.results.DataResult;
import com.example.rent2gojavaproject.core.utilities.results.Result;
import com.example.rent2gojavaproject.core.utilities.results.SuccessDataResult;
import com.example.rent2gojavaproject.core.utilities.results.SuccessResult;
import com.example.rent2gojavaproject.models.Customer;
import com.example.rent2gojavaproject.repositories.CustomerRepository;
import com.example.rent2gojavaproject.services.abstracts.CustomerService;
import com.example.rent2gojavaproject.services.dtos.requests.customerRequest.AddCustomerRequest;
import com.example.rent2gojavaproject.services.dtos.requests.customerRequest.UpdateCustomerRequest;
import com.example.rent2gojavaproject.services.dtos.responses.customerResponse.GetCustomerListResponse;
import com.example.rent2gojavaproject.services.dtos.responses.customerResponse.GetCustomerResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CustomerManager implements CustomerService {
    private CustomerRepository customerRepository;
    private ModelMapperService mapperService;

    @Override
    public DataResult<List<GetCustomerListResponse>> getAllCustomer() {
        List<Customer> customers = this.customerRepository.findAll();
        List<GetCustomerListResponse> responses = customers.stream().map(customer -> this.mapperService.forResponse().map(customer, GetCustomerListResponse.class)).collect(Collectors.toList());
        return new SuccessDataResult<List<GetCustomerListResponse>>(responses, "Transaction Successfully");
    }

    @Override
    public DataResult<GetCustomerResponse> getById(int customerId) {
        Customer customer = this.customerRepository.findById(customerId).orElseThrow(() -> new RuntimeException("Customer not found: " + customerId));
        GetCustomerResponse response = this.mapperService.forResponse().map(customer, GetCustomerResponse.class);
        return new SuccessDataResult<GetCustomerResponse>(response, "Transaction Successfully");

    }

    @Override
    public Result addCustomer(AddCustomerRequest addCustomerRequest) {
        Customer customer = this.mapperService.forRequest().map(addCustomerRequest, Customer.class);
        this.customerRepository.save(customer);

        return new SuccessResult("Transaction Successfully");
    }

    @Override
    public Result updateCustomer(UpdateCustomerRequest updateCustomerRequest) {
        this.customerRepository.findById(updateCustomerRequest.getId()).orElseThrow(() -> new RuntimeException("Customer not found: " + updateCustomerRequest.getId()));
        Customer customer = this.mapperService.forRequest().map(updateCustomerRequest, Customer.class);
        this.customerRepository.save(customer);


        return new SuccessResult("Transaction Successfully");
    }

    @Override
    public Result DeleteCustomer(int customerId) {
        this.customerRepository.findById(customerId).orElseThrow(() -> new RuntimeException("Customer not found: " + customerId));

        this.customerRepository.deleteById(customerId);
        return new SuccessResult("Transaction Successfully");
    }


}
