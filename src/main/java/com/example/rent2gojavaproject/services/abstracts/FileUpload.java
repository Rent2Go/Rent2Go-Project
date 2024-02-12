package com.example.rent2gojavaproject.services.abstracts;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileUpload {

    String uploadFileCar(MultipartFile multipartFile,String uniqColumn) throws IOException;
    String uploadFileUser(MultipartFile multipartFile,String uniqColumn) throws IOException;
}
