package com.example.rent2gojavaproject.services.concretes;

import com.example.rent2gojavaproject.repositories.ImageDataRepository;
import com.example.rent2gojavaproject.services.abstracts.ImageDataService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
@Service
@AllArgsConstructor
public class ImageDataManager implements ImageDataService {

    private final ImageDataRepository dataRepository;
    @Override
    public String uploadImage(MultipartFile file) {
        return null;
    }

    @Override
    public byte[] downloadImage(String fileName) {
        return new byte[0];
    }
}
