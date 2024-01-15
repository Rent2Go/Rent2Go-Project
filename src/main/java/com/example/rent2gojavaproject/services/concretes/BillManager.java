package com.example.rent2gojavaproject.services.concretes;

import com.example.rent2gojavaproject.core.exceptions.NotFoundException;
import com.example.rent2gojavaproject.core.utilities.alerts.Message;
import com.example.rent2gojavaproject.core.utilities.mappers.ModelMapperService;
import com.example.rent2gojavaproject.core.utilities.results.DataResult;
import com.example.rent2gojavaproject.core.utilities.results.Result;
import com.example.rent2gojavaproject.core.utilities.results.SuccessDataResult;
import com.example.rent2gojavaproject.core.utilities.results.SuccessResult;
import com.example.rent2gojavaproject.models.Bill;
import com.example.rent2gojavaproject.repositories.BillRepository;
import com.example.rent2gojavaproject.services.abstracts.BillService;
import com.example.rent2gojavaproject.services.dtos.requests.billRequest.AddBillRequest;
import com.example.rent2gojavaproject.services.dtos.requests.billRequest.UpdateBillRequest;
import com.example.rent2gojavaproject.services.dtos.responses.billResponse.GetBillListResponse;
import com.example.rent2gojavaproject.services.dtos.responses.billResponse.GetBillResponse;
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
public class BillManager implements BillService {
    private BillRepository billRepository;
    private ModelMapperService mapperService;
    private EntityManager entityManager;

    @Override
    public DataResult<List<GetBillListResponse>> getAllBills() {
        List<Bill> bills = this.billRepository.findAll();
        List<GetBillListResponse> responses = bills.stream()
                .map(bill -> this.mapperService.forResponse()
                        .map(bill, GetBillListResponse.class))
                .collect(Collectors.toList());
        return new SuccessDataResult<>(responses, Message.GET_ALL.getMessage());
    }

    @Override
    public DataResult<Iterable<GetBillListResponse>> findAll(boolean isActive) {
        Session session = entityManager.unwrap(Session.class);
        Filter filter = session.enableFilter("isActiveFilterBill");
        filter.setParameter("isActive", isActive);
        Iterable<GetBillListResponse> bills = this.billRepository.findAll().stream()
                .map(bill -> this.mapperService.forResponse()
                        .map(bill, GetBillListResponse.class))
                .collect(Collectors.toList());
        session.disableFilter("isActiveFilterBill");
        return new SuccessDataResult<>(bills, Message.GET_ALL.getMessage());
    }

    @Override
    public DataResult<GetBillResponse> getById(int id) {
        Bill bill = this.billRepository.findById(id).orElseThrow(() -> new NotFoundException("Bill not found: " + id));
        GetBillResponse response = this.mapperService.forResponse().map(bill, GetBillResponse.class);
        return new SuccessDataResult<>(response, Message.GET.getMessage());
    }

    @Override
    public Result addBill(AddBillRequest addBillRequest) {
        Bill bill = this.mapperService.forRequest().map(addBillRequest, Bill.class);
        this.billRepository.save(bill);

        return new SuccessResult(Message.ADD.getMessage());
    }

    @Override
    public Result updateBill(UpdateBillRequest updateBillRequest) {
        this.billRepository.findById(updateBillRequest.getId()).orElseThrow(() -> new NotFoundException("Bill not found "));
        Bill bill = this.mapperService.forRequest().map(updateBillRequest, Bill.class);
        this.billRepository.save(bill);


        return new SuccessResult(Message.UPDATE.getMessage());
    }

    @Override
    public Result deleteBill(int id) {
        Bill bill = this.billRepository.findById(id).orElseThrow(() -> new NotFoundException("id not found : " + id));
        bill.setDeletedAt(LocalDate.now());
        this.billRepository.save(bill);
        this.billRepository.delete(bill);

        return new SuccessResult(Message.DELETE.getMessage());
    }

    @Override
    public boolean existsById(int id) {
        return this.billRepository.existsById(id);
    }
}
