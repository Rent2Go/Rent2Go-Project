package com.example.rent2gojavaproject.services.concretes;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
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
    public String uploadFile(MultipartFile multipartFile,int id) throws IOException {
        String publicId = "rent2go/carImages/" + String.valueOf(id);

        Map params1 = ObjectUtils.asMap(
                "use_filename", multipartFile.getName(),
                "unique_filename", false,
                "overwrite", true,
                "public_id", publicId
        );

        return cloudinary.uploader()
                .upload(  multipartFile.getBytes(),params1)

                .get("url")
                .toString();
    }
}