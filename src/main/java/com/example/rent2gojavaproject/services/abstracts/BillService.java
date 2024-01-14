package com.example.rent2gojavaproject.services.abstracts;

import com.example.rent2gojavaproject.core.utilities.results.DataResult;
import com.example.rent2gojavaproject.core.utilities.results.Result;
import com.example.rent2gojavaproject.services.dtos.requests.billRequest.AddBillRequest;
import com.example.rent2gojavaproject.services.dtos.requests.billRequest.UpdateBillRequest;
import com.example.rent2gojavaproject.services.dtos.responses.billResponse.GetBillListResponse;
import com.example.rent2gojavaproject.services.dtos.responses.billResponse.GetBillResponse;

import java.util.List;

public interface BillService {
    DataResult<List<GetBillListResponse>> getAllBills();

    DataResult<GetBillResponse> getById(int id);

    Result addBill(AddBillRequest addBillRequest);

    Result updateBill(UpdateBillRequest updateBillRequest);

    Result deleteBill(int id);

    DataResult<Iterable<GetBillListResponse>> findAll(boolean isActive);

    boolean existsById(int id);
}
