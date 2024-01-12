package com.example.rent2gojavaproject.services.abstracts;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageDataService {

    String uploadImage(MultipartFile file) throws IOException;

    byte[] downloadImage(String fileName);
}
