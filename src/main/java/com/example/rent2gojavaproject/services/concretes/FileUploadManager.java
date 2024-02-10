package com.example.rent2gojavaproject.services.concretes;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.rent2gojavaproject.core.utilities.constants.CloudinaryConstants;
import com.example.rent2gojavaproject.services.abstracts.FileUpload;
import io.github.cdimascio.dotenv.Dotenv;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileUploadManager implements FileUpload {

    private final Cloudinary cloudinary;

    @Override
    public String uploadFileCar(MultipartFile multipartFile,String uniqColumn) throws IOException {
        String publicId = CloudinaryConstants.BASE_PUBLIC_ID_CAR.getValue() + uniqColumn;

        Map params1 = ObjectUtils.asMap(
                CloudinaryConstants.USE_FILENAME.getValue(), multipartFile.getName(),
                CloudinaryConstants.UNIQUE_FILENAME.getValue(), true,
                CloudinaryConstants.OVERWRITE.getValue(), true,
                CloudinaryConstants.PUBLIC_ID.getValue(), publicId
        );

        return cloudinary.uploader()
                .upload(multipartFile.getBytes(),params1)
                .get(CloudinaryConstants.URL.getValue())
                .toString();
    }

    @Override
    public String uploadFileUser(MultipartFile multipartFile,String uniqColumn) throws IOException {
        String publicId = CloudinaryConstants.BASE_PUBLIC_ID_USER.getValue() + uniqColumn;

        Map params1 = ObjectUtils.asMap(
                CloudinaryConstants.USE_FILENAME.getValue(), multipartFile.getName(),
                CloudinaryConstants.UNIQUE_FILENAME.getValue(), true,
                CloudinaryConstants.OVERWRITE.getValue(), true,
                CloudinaryConstants.PUBLIC_ID.getValue(), publicId
        );

        return cloudinary.uploader()
                .upload(multipartFile.getBytes(),params1)
                .get(CloudinaryConstants.URL.getValue())
                .toString();
    }
}