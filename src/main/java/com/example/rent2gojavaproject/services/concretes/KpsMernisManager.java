package com.example.rent2gojavaproject.services.concretes;



import com.example.rent2gojavaproject.services.abstracts.KpsMernisService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class KpsMernisManager implements KpsMernisService {


    @Override
    public Boolean tcKimlikDoğrula(Long TCKimlikNo, String Ad, String Soyad, Integer DogumYili) throws Exception {




        return true;
    }
}
