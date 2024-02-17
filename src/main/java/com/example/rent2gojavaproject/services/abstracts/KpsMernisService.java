package com.example.rent2gojavaproject.services.abstracts;

import com.example.rent2gojavaproject.core.utilities.results.DataResult;

public interface KpsMernisService {

    Boolean tcKimlikDoğrula(final Long TCKimlikNo,final String Ad,final String Soyad,final Integer DogumYili) throws Exception;
}
