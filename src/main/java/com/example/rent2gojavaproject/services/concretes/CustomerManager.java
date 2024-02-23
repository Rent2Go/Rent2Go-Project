package com.example.rent2gojavaproject.services.concretes;

import com.example.rent2gojavaproject.core.exceptions.NotFoundException;
import com.example.rent2gojavaproject.core.utilities.constants.HibernateConstants;
import com.example.rent2gojavaproject.core.utilities.constants.MessageConstants;
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
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import org.hibernate.Filter;
import org.hibernate.Session;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CustomerManager implements CustomerService {
    private CustomerRepository customerRepository;
    private ModelMapperService mapperService;
    private EntityManager entityManager;

    @Override
    public DataResult<List<GetCustomerListResponse>> getAllCustomer() {

        List<Customer> customers = this.customerRepository.findAll();
        List<GetCustomerListResponse> responses = customers.stream()
                .map(customer -> this.mapperService.forResponse()
                        .map(customer, GetCustomerListResponse.class))
                .collect(Collectors.toList());

        return new SuccessDataResult<>(responses, MessageConstants.GET_ALL.getMessage());
    }

    @Override
    public DataResult<Iterable<GetCustomerListResponse>> findAll(boolean isActive) {

        Session session = entityManager.unwrap(Session.class);
        Filter filter = session.enableFilter(HibernateConstants.IS_ACTIVE_FILTER_CUSTOMER.getValue());

        filter.setParameter(HibernateConstants.IS_ACTIVE.getValue(), isActive);

        Iterable<GetCustomerListResponse> customers = this.customerRepository
                .findAll().stream().map(customer -> this.mapperService.forResponse()
                        .map(customer, GetCustomerListResponse.class))
                .collect(Collectors.toList());
        session.disableFilter(HibernateConstants.IS_ACTIVE_FILTER_CUSTOMER.getValue());

        return new SuccessDataResult<>(customers, MessageConstants.GET_ALL.getMessage());
    }

    @Override
    public DataResult<GetCustomerResponse> getById(int id) {
        Customer customer = this.customerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(MessageConstants.CUSTOMER.getMessage() + MessageConstants.NOT_FOUND.getMessage()));

        GetCustomerResponse response = this.mapperService.forResponse().map(customer, GetCustomerResponse.class);

        return new SuccessDataResult<>(response, MessageConstants.GET.getMessage());
    }

    @Override
    public Result addCustomer(AddCustomerRequest addCustomerRequest) {

        Customer customer = this.mapperService.forRequest().map(addCustomerRequest, Customer.class);
        this.customerRepository.save(customer);

        return new SuccessResult(MessageConstants.ADD.getMessage());
    }

    @Override
    public Result updateCustomer(UpdateCustomerRequest updateCustomerRequest) {

        this.customerRepository.findById(updateCustomerRequest.getId())
                .orElseThrow(() -> new NotFoundException(MessageConstants.CUSTOMER.getMessage() + MessageConstants.NOT_FOUND.getMessage()));
        Customer customer = this.mapperService.forRequest().map(updateCustomerRequest, Customer.class);
        this.customerRepository.save(customer);

        return new SuccessResult(MessageConstants.UPDATE.getMessage());
    }

    @Override
    public Result DeleteCustomer(int id) {

        Customer customer = this.customerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(MessageConstants.ID_NOT_FOUND.getMessage() + id));
        customer.setDeletedAt(LocalDate.now());
        customer.setActive(false);

        this.customerRepository.save(customer);

        return new SuccessResult(MessageConstants.DELETE.getMessage());
    }

    public void hardDeleteCustomer(int id) {
        this.customerRepository.deleteById(id);
    }

    @Override
    public boolean existsById(int id) {
        return this.customerRepository.existsById(id);
    }

    @Override
    public Customer getCustomerByUserId(int userId) {
        return this.customerRepository.getCustomerByUserId(userId);
    }
}
