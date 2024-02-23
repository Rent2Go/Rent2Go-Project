package com.example.rent2gojavaproject.services.abstracts;

import com.example.rent2gojavaproject.core.utilities.results.DataResult;
import com.example.rent2gojavaproject.core.utilities.results.Result;
import com.example.rent2gojavaproject.models.Customer;
import com.example.rent2gojavaproject.services.dtos.requests.customerRequest.AddCustomerRequest;
import com.example.rent2gojavaproject.services.dtos.requests.customerRequest.UpdateCustomerRequest;
import com.example.rent2gojavaproject.services.dtos.responses.customerResponse.GetCustomerListResponse;
import com.example.rent2gojavaproject.services.dtos.responses.customerResponse.GetCustomerResponse;

import java.util.List;

public interface CustomerService {

    DataResult<List<GetCustomerListResponse>> getAllCustomer();

    DataResult<GetCustomerResponse> getById(int customerId);

    Result addCustomer(AddCustomerRequest addCustomerRequest);

    Result updateCustomer(UpdateCustomerRequest updateCustomerRequest);

    Result DeleteCustomer(int customerId);

    DataResult<Iterable<GetCustomerListResponse>> findAll(boolean isActive);

    boolean existsById(int id);

    Customer getCustomerByUserId(int userId);

    void hardDeleteCustomer(int id);
}
