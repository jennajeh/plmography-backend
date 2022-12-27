package kr.jenna.plmography.controllers;

import kr.jenna.plmography.services.S3Uploader;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class S3FileController {
    private final S3Uploader s3Uploader;

    public S3FileController(S3Uploader s3Uploader) {
        this.s3Uploader = s3Uploader;
    }

    @PostMapping("/upload")
    @ResponseStatus(HttpStatus.CREATED)
    public String upload(MultipartFile multipartFile) throws IOException {
        return s3Uploader.uploadFiles(multipartFile, "temporary");
    }
}
