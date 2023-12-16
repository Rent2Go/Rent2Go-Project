package com.example.rent2gojavaproject.services.concretes;

import com.example.rent2gojavaproject.core.utilities.mappers.ModelMapperService;
import com.example.rent2gojavaproject.core.utilities.results.DataResult;
import com.example.rent2gojavaproject.core.utilities.results.Result;
import com.example.rent2gojavaproject.core.utilities.results.SuccessDataResult;
import com.example.rent2gojavaproject.core.utilities.results.SuccessResult;
import com.example.rent2gojavaproject.models.Rental;
import com.example.rent2gojavaproject.repositories.RentalRepository;
import com.example.rent2gojavaproject.services.abstracts.RentalService;
import com.example.rent2gojavaproject.services.dtos.requests.rentalRequest.AddRentalRequest;
import com.example.rent2gojavaproject.services.dtos.requests.rentalRequest.UpdateRentalRequest;
import com.example.rent2gojavaproject.services.dtos.responses.rentalResponse.GetRentalListResponse;
import com.example.rent2gojavaproject.services.dtos.responses.rentalResponse.GetRentalResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class RentalManager implements RentalService {
    private final RentalRepository rentalRepository;
    private ModelMapperService mapperService;
    @Override
    public DataResult<List<GetRentalListResponse>> getAllRentals() {
        List<Rental> rentals = this.rentalRepository.findAll();
        List<GetRentalListResponse> responses = rentals.stream().map(rental -> this.mapperService.forResponse()
                .map(rental,GetRentalListResponse.class)).collect(Collectors.toList());
        return new SuccessDataResult<List<GetRentalListResponse>>(responses,"Transaction Successfully");
    }

    @Override
    public DataResult<GetRentalResponse> getById(int id) {
        Rental rental = this.rentalRepository.findById(id).orElseThrow(() -> new RuntimeException("Couldn't find rental id"));

        GetRentalResponse response = this.mapperService.forResponse().map(rental, GetRentalResponse.class);


        return new SuccessDataResult<GetRentalResponse>(response, "Transaction Successfully");
    }

    @Override
    public Result addRental(AddRentalRequest addRentalRequest) {
        Rental rental = this.mapperService.forRequest().map(addRentalRequest, Rental.class);

        this.rentalRepository.save(rental);
        return new SuccessResult("Added rental successfully");    }

    @Override
    public Result updateRental(UpdateRentalRequest updateRentalRequest) {
        Rental rental = this.rentalRepository.findById(updateRentalRequest.getId()).orElseThrow(() -> new RuntimeException("Couldn't find rental id"));

        rental = this.mapperService.forRequest().map(updateRentalRequest, Rental.class);
        this.rentalRepository.save(rental);
        return new SuccessResult("Updated rental successfully");
    }

    @Override
    public Result deleteRental(int id) {
        this.rentalRepository.findById(id).orElseThrow(() -> new RuntimeException("Couldn't find rental id"));
        this.rentalRepository.deleteById(id);
        return new SuccessResult("Deleted rental successfully");    }
}
