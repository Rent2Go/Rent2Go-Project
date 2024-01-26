package com.example.rent2gojavaproject.services.concretes;

import com.example.rent2gojavaproject.core.exceptions.NotFoundException;
import com.example.rent2gojavaproject.core.utilities.constants.MessageConstants;
import com.example.rent2gojavaproject.core.utilities.constants.HibernateConstants;
import com.example.rent2gojavaproject.core.utilities.mappers.ModelMapperService;
import com.example.rent2gojavaproject.core.utilities.results.DataResult;
import com.example.rent2gojavaproject.core.utilities.results.Result;
import com.example.rent2gojavaproject.core.utilities.results.SuccessDataResult;
import com.example.rent2gojavaproject.core.utilities.results.SuccessResult;
import com.example.rent2gojavaproject.models.Employee;
import com.example.rent2gojavaproject.repositories.EmployeeRepository;
import com.example.rent2gojavaproject.services.abstracts.EmployeeService;
import com.example.rent2gojavaproject.services.dtos.requests.employeeRequest.AddEmployeeRequest;
import com.example.rent2gojavaproject.services.dtos.requests.employeeRequest.UpdateEmployeeRequest;
import com.example.rent2gojavaproject.services.dtos.responses.employeeResponse.GetEmployeeListResponse;
import com.example.rent2gojavaproject.services.dtos.responses.employeeResponse.GetEmployeeResponse;
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
public class EmployeeManager implements EmployeeService {

    private EmployeeRepository employeeRepository;
    private ModelMapperService mapperService;
    private EntityManager entityManager;

    @Override
    public DataResult<List<GetEmployeeListResponse>> getAllEmployees() {

        List<Employee> employees = this.employeeRepository.findAll();
        List<GetEmployeeListResponse> responses = employees.stream()
                .map(employee -> this.mapperService.forResponse()
                        .map(employee, GetEmployeeListResponse.class))
                .collect(Collectors.toList());

        return new SuccessDataResult<>(responses, MessageConstants.GET_ALL.getMessage());
    }

    @Override
    public DataResult<Iterable<GetEmployeeListResponse>> findAll(boolean isActive) {

        Session session = entityManager.unwrap(Session.class);
        Filter filter = session.enableFilter(HibernateConstants.IS_ACTIVE_FILTER_EMPLOYEE.getValue());

        filter.setParameter(HibernateConstants.IS_ACTIVE.getValue(), isActive);

        Iterable<GetEmployeeListResponse> employees = this.employeeRepository.findAll()
                .stream().map(employee -> this.mapperService.forResponse()
                        .map(employee, GetEmployeeListResponse.class))
                .collect(Collectors.toList());
        session.disableFilter(HibernateConstants.IS_ACTIVE_FILTER_EMPLOYEE.getValue());

        return new SuccessDataResult<>(employees, MessageConstants.GET_ALL.getMessage());
    }

    @Override
    public DataResult<GetEmployeeResponse> getById(int id) {

        Employee employee = this.employeeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(MessageConstants.EMPLOYEE.getMessage() + MessageConstants.NOT_FOUND.getMessage()));
        GetEmployeeResponse response = this.mapperService.forResponse().map(employee, GetEmployeeResponse.class);


        return new SuccessDataResult<>(response, MessageConstants.GET.getMessage());
    }

    @Override
    public Result addEmployee(AddEmployeeRequest addEmployeeRequest) {

        Employee employee = this.mapperService.forRequest().map(addEmployeeRequest, Employee.class);

        this.employeeRepository.save(employee);

        return new SuccessResult(MessageConstants.ADD.getMessage());
    }

    @Override
    public Result updateEmployee(UpdateEmployeeRequest updateEmployeeRequest) {

        this.employeeRepository.findById(updateEmployeeRequest.getId()).orElseThrow(() -> new NotFoundException(MessageConstants.EMPLOYEE.getMessage() + MessageConstants.NOT_FOUND.getMessage()));

        Employee employee = this.mapperService.forRequest().map(updateEmployeeRequest, Employee.class);
        this.employeeRepository.save(employee);

        return new SuccessResult(MessageConstants.UPDATE.getMessage());
    }

    @Override
    public Result deleteEmployee(int id) {

        Employee employee = this.employeeRepository.findById(id).orElseThrow(() -> new NotFoundException(MessageConstants.ID_NOT_FOUND.getMessage() + id));
        employee.setDeletedAt(LocalDate.now());
        this.employeeRepository.save(employee);
        this.employeeRepository.delete(employee);

        return new SuccessResult(MessageConstants.DELETE.getMessage());
    }

    @Override
    public boolean existsById(int id) {
        return this.employeeRepository.existsById(id);
    }
}
