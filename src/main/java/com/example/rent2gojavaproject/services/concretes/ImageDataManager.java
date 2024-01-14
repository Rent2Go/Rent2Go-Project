package com.example.rent2gojavaproject.services.concretes;

import com.example.rent2gojavaproject.core.utilities.ImageDataUtils.ImageUtils;
import com.example.rent2gojavaproject.models.ImageData;
import com.example.rent2gojavaproject.repositories.ImageDataRepository;
import com.example.rent2gojavaproject.services.abstracts.ImageDataService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ImageDataManager implements ImageDataService {

    private final ImageDataRepository dataRepository;
    @Override
    public String uploadImage(MultipartFile file) throws IOException {

        ImageData imageData = dataRepository.save(ImageData.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .imageData(ImageUtils.compressImage(file.getBytes())).build());
        if (imageData != null) {
            return "file uploaded successfully : " + file.getOriginalFilename();
        }
        return null;

    }

    @Override
    public byte[] downloadImage(String fileName) {
        Optional<ImageData> dbImageData = dataRepository.findByName(fileName);
        byte[] images=ImageUtils.decompressImage(dbImageData.get().getImageData());
        return images;

    }
}
