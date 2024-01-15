package com.example.rent2gojavaproject.controllers;

import com.example.rent2gojavaproject.services.abstracts.FileUpload;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/V1/files")

public class FileUploadController {

    private final FileUpload fileUpload;



    @PostMapping(value = "/upload", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public String uploadFile(@RequestParam("image") MultipartFile multipartFile,@RequestParam("id") int id
                             ) throws IOException {
        String imageURL = fileUpload.uploadFile(multipartFile,id);
        return imageURL;
    }
}