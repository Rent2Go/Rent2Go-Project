package com.example.rent2gojavaproject.services.concretes;

import com.example.rent2gojavaproject.core.exceptions.IdCardNotValidException;
import com.example.rent2gojavaproject.core.exceptions.NotFoundException;
import com.example.rent2gojavaproject.core.mernis.ABIKPSPublicSoap;
import com.example.rent2gojavaproject.core.utilities.constants.MessageConstants;
import com.example.rent2gojavaproject.core.utilities.results.DataResult;
import com.example.rent2gojavaproject.services.abstracts.KpsMernisService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class KpsMernisManager implements KpsMernisService {


    @Override
    public Boolean tcKimlikDoğrula(Long TCKimlikNo, String Ad, String Soyad, Integer DogumYili) throws Exception {
        ABIKPSPublicSoap abikpsPublicSoap = new ABIKPSPublicSoap();
       Boolean result =  abikpsPublicSoap.TCKimlikNoDogrula(TCKimlikNo,  Ad,  Soyad,  DogumYili);

       if(!result) {
           throw new IdCardNotValidException(MessageConstants.ID_CARD_INFORMATION_NOT_VALID.getMessage());
       }


        return result;
    }
}
