package com.example.rent2gojavaproject.services.abstracts;

import org.springframework.web.multipart.MultipartFile;

public interface ImageDataService {

    String uploadImage(MultipartFile file);

    byte[] downloadImage(String fileName);
}
